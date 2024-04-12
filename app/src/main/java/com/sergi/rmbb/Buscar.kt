package com.sergi.rmbb

import android.content.ContentValues
import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Buscar : AppCompatActivity() {
    private var textId: EditText? = null
    private var textName: EditText? = null
    private var textStatus: EditText? = null
    private var textSpecies: EditText? = null
    private var nameText: EditText? = null
    private var btnFind: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar)

        textId = findViewById(R.id.id)
        textName = findViewById(R.id.nombre)
        textStatus = findViewById(R.id.status)
        textSpecies = findViewById(R.id.species)
        nameText = findViewById(R.id.nombre)
        btnFind = findViewById(R.id.btnFind)

        btnFind?.setOnClickListener {
            find()
        }
    }

    private fun find() {
        val con = SQLite(this, "Dibujos", null, 1)
        val basedatos = con.writableDatabase
        val nombre = nameText?.text.toString()

        if (nombre.isNotEmpty()) {
            val fila: Cursor? = basedatos.rawQuery("SELECT * FROM personajes WHERE name=?", arrayOf(nombre))
            fila?.use { cursor ->
                if (cursor.moveToFirst()) {
                    textId?.setText(cursor.getString(0))
                    textStatus?.setText(cursor.getString(2))
                    textSpecies?.setText(cursor.getString(3))
                } else {
                    Toast.makeText(this, "No se encontró el registro", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(this, "El nombre está vacío", Toast.LENGTH_SHORT).show()
        }
    }
}
