package com.taller1_cm.Logica

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.taller1_cm.R

class DetallesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles)
        val nombre = findViewById<TextView>(R.id.TituloDestinoDetalle)
        val pais = findViewById<TextView>(R.id.paisDetalles)
        val categoria = findViewById<TextView>(R.id.categoriaDetalles)
        val plan = findViewById<TextView>(R.id.planDetalles)
        val precio = findViewById<TextView>(R.id.precioDetalles)
        val botonFav = findViewById<Button>(R.id.botonAddFavoritos)
        val bolsa = intent.getBundleExtra("info")

        setStrings(nombre, pais, categoria,plan,precio,bolsa)
        botonFav.setOnClickListener {  }
    }

    private fun setStrings (nombre: TextView, pais: TextView, categoria: TextView, plan:TextView, precio:TextView, bolsa: Bundle?){
        nombre.setText(bolsa?.getString("nombre"))
        pais.setText(bolsa?.getString("pais"))
        categoria.setText(bolsa?.getString("categoria"))
        plan.setText(bolsa?.getString("plan"))
        var precioInt = bolsa?.getInt("precio")
        precio.setText("USD" + precioInt.toString())
    }

    private fun addFavorite (){

    }
}