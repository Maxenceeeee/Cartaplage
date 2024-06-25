package fr.iia_formation.detailsactivity.gui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import fr.iia_formation.detailsactivity.R
import fr.iia_formation.detailsactivity.databinding.PlageItemBinding
import fr.iia_formation.detailsactivity.domain.Plage

class PlageAdapter (var lesPlages : List<Plage>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private lateinit var binding : PlageItemBinding
    class PlageViewHolder(bd : PlageItemBinding) : RecyclerView.ViewHolder(bd.root) {
        val nom = bd.itemPlageNom
        val description = bd.itemPlageDescription
        val image = bd.itemPlageImage
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.plage_item, parent, false)
        val bd = PlageItemBinding.bind(view)
        return PlageViewHolder(bd)
    }

    override fun getItemCount() = lesPlages.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as PlageViewHolder
        holder.nom.text = lesPlages[position].nom
        holder.description.text = lesPlages[position].descrption
        val it = holder.itemView.context.resources.getIdentifier(
            lesPlages[position].image,
            "drawable",
            holder.itemView.context.packageName
        )
        holder.image.setImageResource(it)
        holder.description.setOnClickListener{
            ListeFragmentDirections.actionListeFragmentToDetailsFragment(position)
                .also {
                    holder.description.findNavController().navigate(it)
                }
        }
    }
}

