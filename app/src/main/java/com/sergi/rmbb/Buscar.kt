package com.sergi.rmbb
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sergi.rmbb.R.layout

class Buscar : AppCompatActivity() {
    private var textId: EditText? = null
    private var textNameF: EditText? = null
    private var textStatus: EditText? = null
    private var textSpecies: EditText? = null
    private var nameText: EditText? = null
    private var btnFind: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_buscar)

        textId = findViewById(R.id.idF)
        textNameF = findViewById(R.id.nombreF)
        textStatus = findViewById(R.id.statusF)
        textSpecies = findViewById(R.id.speciesF)
        nameText = findViewById(R.id.nombreF)
        btnFind = findViewById(R.id.btnFind)

        btnFind?.setOnClickListener {
            find()
        }
    }

    private fun find() {
        val con = SQLite(this, "Dibujos", null, 1)
        val basedatos = con.writableDatabase
        val input = nameText?.text.toString()

        if (input.isNotEmpty()) {
            val query = ("SELECT * FROM personajes WHERE id=? OR name=? OR status=? OR species=?")
            val cursor = basedatos.rawQuery(query, arrayOf(input, input, input, input))

            cursor.use { c ->
                val idIndex = c.getColumnIndex("id")
                val nameIndex = c.getColumnIndex("name")
                val statusIndex = c.getColumnIndex("status")
                val speciesIndex = c.getColumnIndex("species")

                if (idIndex != -1 && nameIndex != -1 && statusIndex != -1 && speciesIndex != -1) {
                    if (c.moveToFirst()) {
                        do {
                            val id = c.getInt(idIndex)
                            val name = c.getString(nameIndex)
                            val status = c.getString(statusIndex)
                            val species = c.getString(speciesIndex)

                        } while (c.moveToNext())
                    }
                } else {
                    Toast.makeText(this, "Error: Columnas no encontradas en la base de datos", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(this, "El campo de búsqueda está vacío", Toast.LENGTH_SHORT).show()
        }
    }

}
