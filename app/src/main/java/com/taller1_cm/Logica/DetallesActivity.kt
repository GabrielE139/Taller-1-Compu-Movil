package com.taller1_cm.Logica

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.taller1_cm.Data.Destino
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
        botonFav.setOnClickListener {
            addFavorite(bolsa)
        }
    }

    private fun setStrings (nombre: TextView, pais: TextView, categoria: TextView, plan:TextView, precio:TextView, bolsa: Bundle?){
        nombre.setText(bolsa?.getString("nombre"))
        pais.setText(bolsa?.getString("pais"))
        categoria.setText(bolsa?.getString("categoria"))
        plan.setText(bolsa?.getString("plan"))
        var precioInt = bolsa?.getInt("precio")
        precio.setText("USD" + precioInt.toString())
    }

    private fun addFavorite (bolsa: Bundle?){
        var bandera = false
        var nombre = bolsa?.getString("nombre")
        var pais = bolsa?.getString("pais")
        var categoria = bolsa?.getString("categoria")
        var plan = bolsa?.getString("plan")
        var precio = bolsa?.getInt("precio")
        for(i in 0 until MainActivity.Favoritos.listaFavs.size){
            if(MainActivity.Favoritos.listaFavs[i].nombre == nombre){
                    bandera = true
            }
        }
        if(bandera){
            Toast.makeText(baseContext, "El destino ya está en su lista de favoritos", Toast.LENGTH_LONG).show()
        }
        else{
            val temporal:Destino = Destino(
                nombre = nombre,
                pais = pais,
                categoria = categoria,
                plan = plan,
                precio = precio
            )
            MainActivity.Favoritos.listaFavs.add(temporal)
            Toast.makeText(baseContext, "Se ha añadido el destino a su lista de favoritos", Toast.LENGTH_LONG).show()
        }
        val peticion1 = Intent(this, DestinosActivity::class.java)
        startActivity(peticion1)
    }
}