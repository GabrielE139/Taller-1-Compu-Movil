package com.taller1_cm.Logica

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.taller1_cm.Data.Destino
import com.taller1_cm.R
import kotlin.random.Random

class RecomendacionesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recomendaciones)

        val nombre = findViewById<TextView>(R.id.TituloDestinoRecomendacion)
        val pais = findViewById<TextView>(R.id.paisRecomendacion)
        val categoria = findViewById<TextView>(R.id.categoriaRecomendacion)
        val plan = findViewById<TextView>(R.id.planRecomendacion)
        val precio = findViewById<TextView>(R.id.precioRecomendacion)

        setStrings(nombre, pais, categoria,plan,precio)


    }

    private fun setStrings (nombre: TextView, pais: TextView, categoria: TextView, plan:TextView, precio:TextView){



        if(MainActivity.Favoritos.listaFavs.size == 0){
            nombre.setText("NA")
            pais.setText(" ")
            categoria.setText(" ")
            plan.setText(" ")
            precio.setText(" ")
        }
        else{



            val catFrequent = frequentCategory()
            val arreglo:MutableList<Destino> = mutableListOf()

            for(i in 0 until MainActivity.Favoritos.listaFavs.size){
                if(MainActivity.Favoritos.listaFavs[i].categoria == catFrequent){
                    arreglo.add(MainActivity.Favoritos.listaFavs[i])
                }
            }



            val indiceAleatorio: Int = Random.nextInt(0, arreglo.size)

            nombre.setText(arreglo[indiceAleatorio].nombre)
            pais.setText(arreglo[indiceAleatorio].pais)
            categoria.setText(arreglo[indiceAleatorio].categoria)
            plan.setText(arreglo[indiceAleatorio].plan)
            precio.setText(arreglo[indiceAleatorio].precio.toString())
        }

    }

    private fun frequentCategory (): String{

        var categoria = " "
        var montana = 0
        var playa = 0
        var ciudadH = 0
        var maravilla = 0
        var selva = 0

        for(i in 0 until MainActivity.Favoritos.listaFavs.size){
            if(MainActivity.Favoritos.listaFavs[i].categoria == "Montañas"){
                montana++
            }
            else if(MainActivity.Favoritos.listaFavs[i].categoria == "Playas"){
                playa++
            }
            else if(MainActivity.Favoritos.listaFavs[i].categoria == "Ciudades Históricas"){
                ciudadH++
            }
            else if(MainActivity.Favoritos.listaFavs[i].categoria == "Maravillas del Mundo"){
                maravilla++
            }
            else if(MainActivity.Favoritos.listaFavs[i].categoria == "Selvas"){
                selva++
            }
        }

        if(montana >= playa && montana >= ciudadH && montana >= maravilla && montana >= selva){
            categoria = "Montañas"
        }
        else if(playa >= montana && playa >= ciudadH && playa >= maravilla && playa >= selva){
            categoria = "Playas"
        }
        else if(ciudadH >= montana && ciudadH >= playa && ciudadH >= maravilla && ciudadH >= selva){
            categoria = "Ciudades Históricas"
        }
        else if(maravilla >= montana && maravilla >= ciudadH && maravilla >= playa && maravilla >= selva){
            categoria = "Maravillas del Mundo"
        }
        else{
            categoria = "Selvas"
        }

        return categoria
    }

}