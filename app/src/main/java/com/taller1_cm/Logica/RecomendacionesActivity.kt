package com.taller1_cm.Logica

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.taller1_cm.R

class RecomendacionesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recomendaciones)

        val nombre = findViewById<TextView>(R.id.TituloDestinoRecomendacion)
        val pais = findViewById<TextView>(R.id.paisRecomendacion)
        val categoria = findViewById<TextView>(R.id.categoriaRecomendacion)
        val plan = findViewById<TextView>(R.id.planRecomendacion)
        val precio = findViewById<TextView>(R.id.precioRecomendacion)


        nombre.setText("NOMBRE")
        pais.setText("PAIS")
        categoria.setText("categoria")
        plan.setText("plan")
        precio.setText("PRECIO")

    }

}