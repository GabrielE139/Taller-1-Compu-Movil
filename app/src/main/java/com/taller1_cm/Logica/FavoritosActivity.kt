package com.taller1_cm.Logica

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.taller1_cm.R

class FavoritosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)

        val lista = findViewById<ListView>(R.id.listViewFav)
        val favoritos = MainActivity.listaFavs

        val itemsToDisplay = if (favoritos.isEmpty()) {
            listOf("N/A")
        } else {
            favoritos.map { it.nombre }
        }

        val adaptador = ArrayAdapter(baseContext, android.R.layout.simple_list_item_1, itemsToDisplay)
        lista.adapter = adaptador
    }

}