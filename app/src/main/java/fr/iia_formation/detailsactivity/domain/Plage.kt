package fr.iia_formation.detailsactivity.domain

import android.os.Bundle
import android.os.Parcelable
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.parcelize.Parcelize

@Parcelize
class Plage (val nom : String,
             val descrption : String,
             val image : String,
             val largeur : Int,
             val longueur : Int,
             val url : String,
             val latitude : Double,
             val longitude : Double
) : Parcelable {
}