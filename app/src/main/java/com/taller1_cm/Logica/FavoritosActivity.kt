package com.taller1_cm.Logica

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
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

        if (favoritos.isNotEmpty()) {
            setupItemClickListener(lista)
        }
    }

    private fun setupItemClickListener(lista: ListView) {
        lista.setOnItemClickListener(object: AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                val selectedItem = MainActivity.listaFavs[pos]
                val peticion = Intent(this@FavoritosActivity, DetallesActivity::class.java)
                val bolsa = Bundle()
                bolsa.putString("nombre", selectedItem.nombre)
                bolsa.putString("pais", selectedItem.pais)
                bolsa.putString("categoria", selectedItem.categoria)
                bolsa.putString("plan", selectedItem.plan)
                bolsa.putInt("precio", selectedItem.precio)
                peticion.putExtras(bolsa)
                startActivity(peticion)
            }
        })
    }
}