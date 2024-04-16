package com.sergi.rmbb

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Introducir : AppCompatActivity() {
    private var textId: EditText? = null
    private var textName: EditText? = null
    private var textStatus: EditText? = null
    private var textSpecies: EditText? = null
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introducir)

        textId = findViewById(R.id.id)
        textName = findViewById(R.id.nombre)
        textStatus = findViewById(R.id.status)
        textSpecies = findViewById(R.id.species)
        button = findViewById(R.id.btninsert)

        button.setOnClickListener {
            insert()
        }
    }

    private fun insert() {
        val id = textId?.text.toString()
        val name = textName?.text.toString()
        val status = textStatus?.text.toString()
        val species = textSpecies?.text.toString()

        if (id.isNotEmpty() && name.isNotEmpty() && status.isNotEmpty() && species.isNotEmpty()) {
            val con = SQLite(this, "Dibujos", null, 1)
            val basedatos = con.writableDatabase
            val query = ("INSERT INTO personajes (id, name, status, species) VALUES (?, ?, ?, ?)")
            basedatos.execSQL(query, arrayOf(id, name, status, species))

            Toast.makeText(this@Introducir, "Insertado con Éxito", Toast.LENGTH_LONG).show()

            // Limpiar los campos después de la inserción
            textId?.setText("")
            textName?.setText("")
            textStatus?.setText("")
            textSpecies?.setText("")
        } else {
            Toast.makeText(this, "Fallo al insertar datos: algún campo está vacío", Toast.LENGTH_LONG).show()
        }
    }
}