package com.taller1_cm.Logica

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.taller1_cm.R
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

class DestinosActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destinos)

        val categoria = intent.getStringExtra("categoria")

        val arreglo = categoria?.let { loadDestinos(it) }

        val lista = findViewById<ListView>(R.id.listViewDes)
        val adaptador =
            arreglo?.let { ArrayAdapter(baseContext, android.R.layout.simple_list_item_1, it.map { it.getString("nombre") }) }
        lista.adapter = adaptador
        if (arreglo != null) {
            setupItemClickListener(lista, arreglo)
        }
    }

    private fun loadDestinos(categoria: String): MutableList<JSONObject> {
        val json = JSONObject(loadJSONFromAsset())
        val paisesJson = json.getJSONArray("destinos")
        val arreglo = mutableListOf<JSONObject>()

        for (i in 0 until paisesJson.length()) {
            val jsonObject = paisesJson.getJSONObject(i)
            if (categoria == "Todos" || jsonObject.getString("categoria") == categoria) {
                arreglo.add(jsonObject)
            }
        }
        return arreglo
    }

    private fun setupItemClickListener(lista: ListView, destinos: List<JSONObject>) {
        lista.setOnItemClickListener(object: AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                val selectedItem = destinos[pos]
                val peticion = Intent(this@DestinosActivity, DetallesActivity::class.java)
                val bolsa = Bundle()
                bolsa.putString("nombre", selectedItem.getString("nombre"))
                bolsa.putString("pais", selectedItem.getString("pais"))
                bolsa.putString("categoria", selectedItem.getString("categoria"))
                bolsa.putString("plan", selectedItem.getString("plan"))
                bolsa.putInt("precio", selectedItem.getInt("precio"))

                peticion.putExtra("info", bolsa)
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