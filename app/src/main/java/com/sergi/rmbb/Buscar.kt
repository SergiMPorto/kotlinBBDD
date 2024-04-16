package com.sergi.rmbb
import android.database.Cursor
import android.os.Bundle
import android.util.Log
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

    private fun find() {
        val input = textId.text.toString()
        if (input.isNotEmpty()) {
            val con = SQLite(this, "Dibujos", null, 1)
            val basedatos = con.readableDatabase
            val query = "SELECT * FROM personajes WHERE id=?"
            val cursor: Cursor? = basedatos.rawQuery(query, arrayOf(input))
            cursor?.use { c ->
                Log.d("Buscar", "Número de filas en el cursor: ${c.count}")
                if (c != null && c.count > 0 && c.moveToFirst()) {
                    val name = c.getString(c.getColumnIndex("name"))
                    val status = c.getString(c.getColumnIndex("status"))
                    val species = c.getString(c.getColumnIndex("species"))

                    Log.d("Buscar", "Nombre: $name, Estado: $status, Especie: $species")

                    // Mostrar la información en los TextView
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