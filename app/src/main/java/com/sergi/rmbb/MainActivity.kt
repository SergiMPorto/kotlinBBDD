package com.sergi.rmbb

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseAuth = FirebaseAuth.getInstance()
        val btnLogin: Button = findViewById(R.id.login)
        val btnRegistro: TextView = findViewById(R.id.botonRegistro)

        btnLogin.setOnClickListener {
            signIn(findViewById<EditText>(R.id.email).text.toString(), findViewById<EditText>(R.id.password).text.toString())
        }

        btnRegistro.setOnClickListener {
            create(findViewById<EditText>(R.id.email).text.toString(), findViewById<EditText>(R.id.password).text.toString())
        }
    }

    private fun signIn(email: String, password: String) {
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Introduzca contraseña y correo electrónico", Toast.LENGTH_SHORT).show()
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
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Introduzca contraseña y correo electrónico", Toast.LENGTH_SHORT).show()
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
