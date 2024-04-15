package com.sergi.rmbb


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnEnter: Button = findViewById(R.id.insertar)

        btnEnter.setOnClickListener {
            val intent = Intent(this, Introducir::class.java)
            startActivity(intent)
        }

        val btnUpdate: Button = findViewById(R.id.modificar)

        btnUpdate.setOnClickListener {
            val intent = Intent(this, Modificar::class.java)
            startActivity(intent)
        }

        val btnFind: Button = findViewById(R.id.buscar)

        btnFind.setOnClickListener {
            val intent = Intent(this, Buscar::class.java)
            startActivity(intent)
        }

        val btnlistar: Button = findViewById(R.id.listar)

        btnlistar.setOnClickListener {
            val intent = Intent(this, Listar::class.java)
            startActivity(intent)
        }

        val btnBorrar: Button = findViewById(R.id.borrar)

        btnBorrar.setOnClickListener {
            val intent = Intent(this, Borrar::class.java)
            startActivity(intent)
        }


    }


}//