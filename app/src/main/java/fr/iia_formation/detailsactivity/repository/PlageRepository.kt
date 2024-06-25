package fr.iia_formation.detailsactivity.repository

import android.content.Context
import fr.iia_formation.detailsactivity.domain.Plage
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.nio.file.Files.createFile

class PlageRepository {
    lateinit var file : File

    fun createFile(filesDir: File, s: String){
        file = File(filesDir, s)
        if (!file.exists()){
            file.createNewFile()
        }
    }

    fun loadPlageFromFile(context: Context) : List<Plage> {
        createFile(context.filesDir, "plage.csv")

        val plages = mutableListOf<Plage>()

        try{
            val reader = BufferedReader(FileReader(file))
            var line : String?

            while (reader.readLine().also{ line = it } != null){
                val plageData = line?.split("+")
                if (plageData != null && plageData.size == 8){
                    val plage = Plage(
                        plageData[0],
                        plageData[1],
                        plageData[2],
                        plageData[3].toInt(),
                        plageData[4].toInt(),
                        plageData[5],
                        plageData[6].toDouble(),
                        plageData[7].toDouble()
                    )
                    plages.add(plage)
                }
            }
            reader.close()
        } catch (e : Exception){
            e.printStackTrace()
        }
        return plages
    }

    fun addPlage(context: Context, plage: Plage){
        createFile(context.filesDir, "plage.csv")
        try {
            val writer = FileWriter(file, true)
            writer.write("${plage.nom}+" +
                    "${plage.descrption}+" +
                    "${plage.image}+" +
                    "${plage.url}+" +
                    "${plage.largeur}+" +
                    "${plage.longueur}+" +
                    "${plage.latitude}+" +
                    "${plage.longitude}\n")
            writer.close()
        }catch (e : Exception){
            e.printStackTrace()
        }
    }
}