package com.taller1_cm.Logica

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.taller1_cm.R
import com.taller1_cm.Data.Destino


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.spinner1)
        spinner.onItemSelectedListener = this

        val boton1 = findViewById<Button>(R.id.button1)
        val boton2 = findViewById<Button>(R.id.button2_Favoritos)
        val boton3 = findViewById<Button>(R.id.button3_Recomendaciones)


        val peticion2 = Intent(this, FavoritosActivity::class.java) //Peticion a Favoritos
        val peticion3 = Intent(this, RecomendacionesActivity::class.java) //Peticion a Recomendaciones

        boton1.setOnClickListener(){ //Ir a Destinos
            operandingButton1(this, spinner, DestinosActivity::class.java)
        }
        boton2.setOnClickListener(){ //Ir a Favoritos
            startActivity(peticion2)
        }
        boton3.setOnClickListener(){ //Ir a Recomendaciones
            startActivity(peticion3)
        }
    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {



    }
    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    private fun operandingButton1(context: Context, spinner: Spinner, activityClass: Class<*>) {
        val peticion1 = Intent(context, activityClass) //Peticion a Destinos
        val categoria = spinner.selectedItem.toString()
        peticion1.putExtra("Categoria spinner", categoria)
        context.startActivity(peticion1)
    }
        companion object Favoritos {
        val listaFavs: MutableList<Destino> = mutableListOf()
    }

}