package fr.iia_formation.detailsactivity.gui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import fr.iia_formation.detailsactivity.R
import fr.iia_formation.detailsactivity.databinding.FragmentAjouterPlageBinding
import fr.iia_formation.detailsactivity.databinding.FragmentDetailsBinding
import fr.iia_formation.detailsactivity.domain.Plage
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.ScaleBarOverlay


class AjouterPlageFragment : Fragment() {
    private lateinit var binding : FragmentAjouterPlageBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAjouterPlageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.enregistrer.setOnClickListener(){
            val plage = Plage(binding.creationTitle.text.toString(),
                binding.creationDescription.text.toString(),
                binding.creationImage.text.toString(),
                binding.creationLargeur.text.toString().toInt(),
                binding.creationLongueur.text.toString().toInt(),
                binding.creationUrl.text.toString(),
                binding.creationLatitude.text.toString().toDouble(),
                binding.creationLongitude.text.toString().toDouble())

            setFragmentResult("request_key", bundleOf("plage" to plage))
            findNavController().navigateUp()

        }
    }
}

