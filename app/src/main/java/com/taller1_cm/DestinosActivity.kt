package com.taller1_cm

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

class DestinosActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destinos)

        //val categoria = intent.getStringExtra("categoria")

        val categoria = "Todos"
        val arreglo = loadDestinos(categoria)

        val lista = findViewById<ListView>(R.id.listViewDes)
        val adaptador = ArrayAdapter(baseContext, android.R.layout.simple_list_item_1, arreglo)
        lista.adapter = adaptador
        setupItemClickListener(lista)
    }

    private fun loadDestinos(categoria: String): MutableList<String> {
        val json = JSONObject(loadJSONFromAsset())
        val paisesJson = json.getJSONArray("destinos")
        val arreglo = mutableListOf<String>()

        for (i in 0 until paisesJson.length()) {
            val jsonObject = paisesJson.getJSONObject(i)
            if (categoria == "Todos" || jsonObject.getString("categoria") == categoria) {
                arreglo.add(jsonObject.getString("nombre"))
            }
        }
        return arreglo
    }

    private fun setupItemClickListener(lista: ListView) {
        val peticion = Intent(this, DetallesActivity::class.java)
        val bolsa = Bundle()

        lista.setOnItemClickListener(object: AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(pos).toString()
                bolsa.putString("nombre", selectedItem)
                peticion.putExtras(bolsa)
                startActivity(peticion)
            }
        })
    }

    private fun loadJSONFromAsset(): String? {
        var json: String? = null
        try {
            val isStream: InputStream = assets.open("destinos.json")
            val size:Int = isStream.available()
            val buffer = ByteArray(size)
            isStream.read(buffer)
            isStream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }


}