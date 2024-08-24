package com.taller1_cm.Logica

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.taller1_cm.Data.Destino
import com.taller1_cm.R
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

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
        obtenerClima(nombre.text.toString())
    }

    private fun obtenerClima(city: String) {
        val clima = findViewById<TextView>(R.id.climaDetalles)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(IWeatherService::class.java)
        val call = service.getCurrentWeather(city, "7dc64ee5f1934d7cabe193311242702")

        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    val weatherData = response.body()
                    if (weatherData != null) {
                        clima.text = weatherData.current.temp_c.toString() + " °C"
                    }
                } else {
                    clima.text = "No se pudo obtener el clima"
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                clima.text = "No se pudo obtener el clima"
            }
        })
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
        val peticion1 = Intent(this, MainActivity::class.java)
        startActivity(peticion1)
    }
}