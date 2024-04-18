package com.sergi.rmbb

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Home : AppCompatActivity() {


    private lateinit var btnListar: Button
    private lateinit var btInsertar : Button
    private lateinit var btnOrden : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnListar = findViewById(R.id.listar) // Inicializar el botón

        btnListar.setOnClickListener {
            startActivity(Intent(this, Listar::class.java))
        }

        val btnFind: Button = findViewById(R.id.buscar)
        btnFind.setOnClickListener {
            val intent = Intent(this, Buscar::class.java)
            startActivity(intent)
        }

       btInsertar= findViewById(R.id.insertar)
        btInsertar.setOnClickListener {
            val intent = Intent(this,Introducir::class.java)
            startActivity(intent)
        }

        btnOrden=findViewById(R.id.ordenByName)
        btnOrden.setOnClickListener {
            val intent = Intent(this, OrdenarPorNombre::class.java)
            startActivity(intent)
        }


    }
}











   /* }

    private fun obtenerYGuardarPersonajes() {
        val apiService = RetrofitService.apiService
        val call = apiService.getCharacters()

        call.enqueue(object : Callback<RespuestaPersonajes> {
            override fun onResponse(
                call: Call<RespuestaPersonajes>,
                response: Response<RespuestaPersonajes>
            ) {
                if (response.isSuccessful) {
                    guardarPersonajesEnBaseDeDatos(response.body()?.results)
                } else {
                    Toast.makeText(
                        this@Home,
                        "Error al obtener los personajes",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<RespuestaPersonajes>, t: Throwable) {
                Toast.makeText(this@Home, "Error de red: ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun guardarPersonajesEnBaseDeDatos(personajes: List<Personaje>?) {
        if (personajes.isNullOrEmpty()) {
            return
        }

        val dbHelper = SQLite(this, "nombre_de_tu_base_de_datos", null, 1)

        for (personaje in personajes) {
            val valores = ContentValues().apply {
                put("name", personaje.name)
                put("status", personaje.status)
                put("species", personaje.species)
            }
            val resultado = dbHelper.insertarPersonaje(valores)
            if (resultado != -1L) {
                println("Personaje insertado con éxito.")
            } else {
                println("Error al insertar el personaje.")
            }
        }

        Toast.makeText(this, "Personajes guardados en la base de datos", Toast.LENGTH_SHORT)
            .show()
    }

}
*/