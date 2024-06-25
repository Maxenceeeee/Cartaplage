package fr.iia_formation.detailsactivity.gui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import fr.iia_formation.detailsactivity.databinding.FragmentListeBinding
import fr.iia_formation.detailsactivity.domain.Plage
import fr.iia_formation.detailsactivity.presentation.PlageViewModel

class ListeFragment : Fragment() {
    private val viewModel : PlageViewModel by activityViewModels()
    private lateinit var binding : FragmentListeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PlageAdapter(viewModel.plages.value ?: listOf<Plage>())
        viewModel.plages.observe(viewLifecycleOwner){
            adapter.lesPlages = it
            adapter.notifyDataSetChanged()
        }
        binding.listePlages.adapter = adapter
        binding.listePlages.layoutManager = LinearLayoutManager(requireContext())
        binding.allerVersCreation.setOnClickListener(){
            setFragmentResultListener("request_key"){ key, bundle ->
                val plage = bundle.getParcelable<Plage>("plage")
                viewModel.addPlage(plage!!)
            }
            val action = ListeFragmentDirections.actionListeFragmentToAjouterPlageFragment()
            findNavController().navigate(action)
        }


    }

}