package com.sergi.rmbb

import android.database.Cursor
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.sergi.rmbb.SQLite.Companion.COLUMN_NAME
import com.sergi.rmbb.SQLite.Companion.TABLE_NAME

private lateinit var listView: ListView
private lateinit var adapter: PersonajesAdapter
private lateinit var dibujosDBHelper: SQLite

class OrdenarPorNombre : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var adapter: PersonajesAdapter
    private lateinit var dibujosDBHelper: SQLite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ordenar_por_nombre)

        listView = findViewById(R.id.listViewOrden)
        adapter = PersonajesAdapter(this, ArrayList())
        listView.adapter = adapter




        ordenarPorNombre()
    }

    private fun ordenarPorNombre() {
        val personajes = mutableListOf<Personaje>()
        val basedatos = dibujosDBHelper.readableDatabase

        val query = "SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_NAME"
        val cursor: Cursor? = basedatos.rawQuery(query, null)

        cursor?.use {
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