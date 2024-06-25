package fr.iia_formation.detailsactivity.gui

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import android.view.LayoutInflater
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import fr.iia_formation.detailsactivity.R
import fr.iia_formation.detailsactivity.databinding.ActivityPlageBinding
import org.osmdroid.config.Configuration
import java.io.File
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import fr.iia_formation.detailsactivity.framework.AlarmReceiver
import fr.iia_formation.detailsactivity.framework.NotificationService

class PlageActivity : AppCompatActivity() {
    val TAG = "PlageActivity"
    private var hasNotificationPermissionGranted = false
    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {isGranted ->
            hasNotificationPermissionGranted = true
            if (!isGranted){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (Build.VERSION.SDK_INT >= 33) {
                        if (shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)) {
                            shouldNotificationPermissionrationale()
                        } else {
                            showSettingDialog()
                        }
                    }
                }
            } else {
                Toast.makeText(applicationContext, "notifications autorisées", Toast.LENGTH_SHORT)
                    .show()
                engageTimer()
            }
        }
    private lateinit var binding : ActivityPlageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_plage)

        val osmConf = Configuration.getInstance()
        val basePath = File(getCacheDir().getAbsolutePath(), "osmdroid")
        osmConf.osmdroidBasePath = basePath
        val tileCache = File(osmConf.osmdroidBasePath.absolutePath, "tile")
        osmConf.osmdroidTileCache = tileCache
        osmConf.setUserAgentValue(packageName)

        binding = ActivityPlageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.liste.setOnClickListener{
            findNavController(R.id.nav_hos_fragment).navigate(R.id.listeFragment)
        }

        binding.notifications.setOnClickListener{
            lancerService()
        }

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_hos_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        var appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean{
        return Navigation.findNavController(this, R.id.nav_hos_fragment).navigateUp()
                || super.onSupportNavigateUp()
    }

    private fun lancerService() {
        Log.d(TAG, "Lancement du service")

        if (Build.VERSION.SDK_INT >= 33) {
            notificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        } else {
            hasNotificationPermissionGranted = true
        }

        if (hasNotificationPermissionGranted){
            engageTimer()
        }
    }

    private fun showSettingDialog(){
        MaterialAlertDialogBuilder(this, com.google.android.material.R.style.MaterialAlertDialog_Material3)
            .setTitle("Notification permission")
            .setMessage("Les notifications doivent être activées pour cette application. Voulez vous activer les notifications ?")
            .setPositiveButton("Ok") {_, _ ->
                val intent = Intent(ACTION_APPLICATION_DETAILS_SETTINGS)
                intent.data = Uri.parse("package:$packageName")
                startActivity(intent)
            }
            .setNegativeButton("cancel", null)
            .show()
    }

    private fun shouldNotificationPermissionrationale() {
        MaterialAlertDialogBuilder(this, com.google.android.material.R.style.MaterialAlertDialog_Material3)
            .setTitle("Notification permission")
            .setMessage("Les notifications doivent être activées pour cette application. Voulez vous activer les notifications ?")
            .setPositiveButton("Ok") {_, _ ->
                if (Build.VERSION.SDK_INT >= 33) {
                    notificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
                }
            }
            .setNegativeButton("cancel", null)
            .show()
    }

    private fun engageTimer() {
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis(),
            1000 * 60 * 1,
            pendingIntent
        )
    }

}