package com.sergi.rmbb

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLite(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE personajes (id INTEGER PRIMARY KEY, name TEXT, status TEXT, species TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun eliminarPersonaje(nombre: String): Int {
        val db = this.writableDatabase
        return db.delete("personajes", "name = ?", arrayOf(nombre))
    }

    fun verificarExistencia(nombre: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM personajes WHERE name = ?", arrayOf(nombre))
        val existe = cursor.count > 0
        cursor.close()
        return existe
    }

    fun modificarPersonaje(id: Int, nombre: String, status: String, species: String): Int {
        val db = this.writableDatabase
        val valores = ContentValues().apply {
            put("name", nombre)
            put("status", status)
            put("species", species)
        }
        return db.update("personajes", valores, "id = ?", arrayOf(id.toString()))
    }

    fun verificarExistencia(id: Int): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM personajes WHERE id = ?", arrayOf(id.toString()))
        val existe = cursor.count > 0
        cursor.close()
        return existe
    }


}
