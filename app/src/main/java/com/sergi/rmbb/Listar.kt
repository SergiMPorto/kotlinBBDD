package com.sergi.rmbb

import RespuestaPersonajes
import RetrofitService
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Listar : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: PersonajesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar)

        listView = findViewById(R.id.listView)

        adapter = PersonajesAdapter(this, ArrayList())
        listView.adapter = adapter

        obtenerPersonajes()
    }

    override fun onResume() {
        super.onResume()
        // Actualizar la lista de personajes cada vez que la actividad vuelve a primer plano
        obtenerPersonajes()
    }

    private fun obtenerPersonajes() {
        val apiService = RetrofitService.apiService
        val call = apiService.getCharacters()

        call.enqueue(object : Callback<RespuestaPersonajes> {
            override fun onResponse(call: Call<RespuestaPersonajes>, response: Response<RespuestaPersonajes>) {
                if (response.isSuccessful) {
                    val personajes = response.body()?.results
                    personajes?.let {
                        adapter.actualizarPersonajes(it)
                    }
                } else {
                    Toast.makeText(this@Listar, "Error al obtener los personajes", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RespuestaPersonajes>, t: Throwable) {
                Toast.makeText(this@Listar, "Error de red: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}