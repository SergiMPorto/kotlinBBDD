package com.sergi.rmbb

import RespuestaPersonajes
import RetrofitService
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    private lateinit var recuperar: Button
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dibujosDBHelper: SQLite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseAuth = FirebaseAuth.getInstance()
        dibujosDBHelper = SQLite(this)

        val btnLogin: Button = findViewById(R.id.login)
        val btnRegistro: TextView = findViewById(R.id.botonRegistro)
        val recuperar: Button = findViewById(R.id.recuperar)

        btnLogin.setOnClickListener {
            signIn(
                findViewById<EditText>(R.id.email).text.toString(),
                findViewById<EditText>(R.id.password).text.toString()
            )
        }

        btnRegistro.setOnClickListener {
            create(
                findViewById<EditText>(R.id.email).text.toString(),
                findViewById<EditText>(R.id.password).text.toString()
            )
        }

        recuperar.setOnClickListener {
            val intent = Intent(this, Recuperar::class.java)
            startActivity(intent)
        }

        //API Y GUARDARLO A LA PUTA BBDD
        val apiService = RetrofitService.apiService
        apiService.getCharacters().enqueue(object : Callback<RespuestaPersonajes> {
            override fun onResponse(call: Call<RespuestaPersonajes>, response: Response<RespuestaPersonajes>) {
                if (response.isSuccessful) {
                    val personajes = response.body()?.results ?: emptyList()
                    val db = dibujosDBHelper.writableDatabase
                    for (personaje in personajes) {

                        val imageUrl = "https://rickandmortyapi.com/api/character/avatar/${personaje.id}"

                        val personajeConImagen = Personaje(personaje.id, personaje.name, personaje.status, personaje.species)
                        // Agregar el personaje a la base de datos
                        dibujosDBHelper.addPersonaje(personajeConImagen, db)
                    }
                    db.close()
                } else {
                    Toast.makeText(this@MainActivity, "ERROR EN LA PUTA API A OBTENER LOS DATOS", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RespuestaPersonajes>, t: Throwable) {
                Toast.makeText(this@MainActivity,"La Puta Api no contesta", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun signIn(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Introduzca contraseña y correo electrónico", Toast.LENGTH_SHORT)
                .show()
            return
        }
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, Home::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Email o Contraseña incorrecta", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun create(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Introduzca contraseña y correo electrónico", Toast.LENGTH_SHORT)
                .show()
            return
        }
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    Toast.makeText(this, "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, Home::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Error al crear la cuenta", Toast.LENGTH_SHORT).show()
                }
            }
    }
}