package fr.iia_formation.detailsactivity.gui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import fr.iia_formation.detailsactivity.R
import fr.iia_formation.detailsactivity.databinding.FragmentDetailsBinding
import fr.iia_formation.detailsactivity.databinding.FragmentListeBinding
import fr.iia_formation.detailsactivity.presentation.PlageViewModel
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.ScaleBarOverlay

class DetailsFragment : Fragment() {
    private val viewModel : PlageViewModel by activityViewModels()
    private lateinit var  binding : FragmentDetailsBinding

    private var plageId : Int = -1
    private val TAG = "DetailsFragment"
    val args : DetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        plageId = args.plageId
        Log.d(TAG, "L'id de la plage est $plageId")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.plages.value?.let {
            val plage = it[plageId]

            binding.mainTitreTv.text = plage.nom
            binding.mainDescriptionTv.text = plage.descrption
            val it = resources.getIdentifier(
                plage.image,
                "drawable",
                requireContext().packageName
            )
            binding.mainImageTv.setImageResource(it)
            binding.mainLTv.text = plage.longueur.toString()
            binding.mainLoTv.text = plage.largeur.toString()
            binding.mainLienTv.text = "En savoir plus"

            binding.mainLienTv.setOnClickListener{
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(plage.url))
                startActivity(intent)
            }

            val startPoint =
                GeoPoint(plage.latitude, plage.longitude)
            binding.imageMapPlage.setUseDataConnection(true)
            binding.imageMapPlage.zoomController.setZoomInEnabled(true)
            binding.imageMapPlage.zoomController.setZoomOutEnabled(true)
            binding.imageMapPlage.setMultiTouchControls(true)
            binding.imageMapPlage.getController().setCenter(startPoint)
            binding.imageMapPlage.getController().setZoom(13.5)
            val myScaleBarOverlay = ScaleBarOverlay(binding.imageMapPlage)
            binding.imageMapPlage.getOverlays().add(myScaleBarOverlay)
            val startMarker = Marker(binding.imageMapPlage)
            startMarker.position = startPoint
            startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            binding.imageMapPlage.getOverlays().add(startMarker)
        }
    }

}