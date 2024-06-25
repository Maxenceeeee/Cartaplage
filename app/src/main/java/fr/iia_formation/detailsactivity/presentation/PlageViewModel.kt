package fr.iia_formation.detailsactivity.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.iia_formation.detailsactivity.domain.Plage

class PlageViewModel : ViewModel() {
    private val _plages = MutableLiveData<List<Plage>>()

    val plages : LiveData<List<Plage>>
        get() = _plages

    fun setPlages(plages : List<Plage>){
        _plages.value = plages
    }

    fun addPlage(plage : Plage) {
        val plages = _plages.value?.toMutableList() ?: mutableListOf()
        plages.add(plage)
        _plages.value = plages
    }

    init {
        _plages.value = listOf(
            Plage("La Palmyre les Mathes", "La Palmyre est un quartier résidentiel et touristique", "lapalmyre", 100, 10, "https://fr.wikipedia.org/wiki/La_Palmyre", 45.69027, -1.17972),
            Plage("La Plage de Virechat", "Petite anse située à l’ouest de Saint-Nazaire, la plage de Virechat est idéale pour les amoureux de nature notamment grâce à son paysage préservé.", "plage", 100, 10, "https://www.saint-nazaire-tourisme.com/offres/plage-de-virechat-saint-nazaire-fr-2537568/", 47.24755, -2.25994),
            Plage("La Plage de Mr Hulot", "La plage de Monsieur Hulot à Saint-Marc est considérée par beaucoup comme la plus belle plage de Saint-Nazaire. Cette vaste plage de sable porte encore le souvenir de Monsieur Hulot, héros du célèbre film", "mrhulot", 100, 10, "https://www.saint-nazaire-tourisme.com/offres/plage-de-monsieur-hulot-saint-nazaire-fr-2501015/", 46.22763, -2.2805)
        )
    }
}