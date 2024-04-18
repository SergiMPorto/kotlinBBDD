package com.sergi.rmbb

import android.annotation.SuppressLint
import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Buscar : AppCompatActivity() {
    private lateinit var textId: EditText
    private lateinit var tvName: TextView
    private lateinit var tvStatus: TextView
    private lateinit var tvSpecies: TextView
    private lateinit var btnFind: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar)

        textId = findViewById(R.id.idF)
        tvName = findViewById(R.id.tvName)
        tvStatus = findViewById(R.id.tvStatus)
        tvSpecies = findViewById(R.id.tvSpecies)
        btnFind = findViewById(R.id.btnFind)

        btnFind.setOnClickListener {
            find()
        }
    }

    @SuppressLint("Range")
    private fun find() {
        val input = textId.text.toString()
        if (input.isNotEmpty()) {
            val con = SQLite(this, "dibujos", null, 1)

            val basedatos = con.readableDatabase
            val query = "SELECT * FROM ${SQLite.TABLE_NAME} WHERE ${SQLite.COLUMN_ID}=?"
            val cursor: Cursor? = basedatos.rawQuery(query, arrayOf(input))
            cursor?.use { c ->
                if (c.moveToFirst()) {
                    val name = c.getString(c.getColumnIndex(SQLite.COLUMN_NAME))
                    val status = c.getString(c.getColumnIndex(SQLite.COLUMN_STATUS))
                    val species = c.getString(c.getColumnIndex(SQLite.COLUMN_SPECIES))

                    tvName.text = "Nombre: $name"
                    tvStatus.text = "Estado: $status"
                    tvSpecies.text = "Especie: $species"
                } else {
                    Toast.makeText(this, "No se encontró ningún personaje con ese ID", Toast.LENGTH_SHORT).show()
                }
            }

        } else {
            Toast.makeText(this, "El campo de búsqueda está vacío", Toast.LENGTH_SHORT).show()
        }
    }
}