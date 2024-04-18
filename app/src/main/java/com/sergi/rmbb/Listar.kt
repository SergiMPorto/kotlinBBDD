package com.sergi.rmbb

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class Listar : AppCompatActivity() {


    private lateinit var listView: ListView
    private lateinit var adapter: PersonajesAdapter
    private lateinit var dibujosDBHelper: SQLite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar)

        listView = findViewById(R.id.listView)
        adapter = PersonajesAdapter(this, ArrayList())
        listView.adapter = adapter


        dibujosDBHelper = SQLite(this)


        obtenerPersonajesDesdeBD()
    }

    override fun onResume() {
        super.onResume()

        obtenerPersonajesDesdeBD()
    }

    private fun obtenerPersonajesDesdeBD() {
        val db = dibujosDBHelper.readableDatabase
        val personajes = mutableListOf<Personaje>()


        val cursor = db.rawQuery("SELECT * FROM ${SQLite.TABLE_NAME}", null)
        cursor.use {
            while (it.moveToNext()) {
                val id = it.getInt(it.getColumnIndex(SQLite.COLUMN_ID))
                val name = it.getString(it.getColumnIndex(SQLite.COLUMN_NAME))
                val status = it.getString(it.getColumnIndex(SQLite.COLUMN_STATUS))
                val species = it.getString(it.getColumnIndex(SQLite.COLUMN_SPECIES))





                val personaje = Personaje(id, name, status, species)
                personajes.add(personaje)
            }
        }


        adapter.actualizarPersonajes(personajes)
    }
}