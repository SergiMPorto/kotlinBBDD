package com.sergi.rmbb

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SQLite(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "dibujos.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "dibujos"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_STATUS = "status"
        const val COLUMN_SPECIES = "species"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableSQL = "CREATE TABLE $TABLE_NAME " +
                "($COLUMN_ID INTEGER PRIMARY KEY, " +
                "$COLUMN_NAME TEXT, " +
                "$COLUMN_STATUS TEXT, " +
                "$COLUMN_SPECIES TEXT,"

        db?.execSQL(createTableSQL)
        Log.d("DibujosDBHelper", "Database created")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Implement if needed
    }

    fun addPersonaje(personaje: Personaje, db: SQLiteDatabase) {
        val values = ContentValues().apply {
            put(COLUMN_ID, personaje.id)
            put(COLUMN_NAME, personaje.name)
            put(COLUMN_STATUS, personaje.status)
            put(COLUMN_SPECIES, personaje.species)
        }
        db.insert(TABLE_NAME, null, values)
    }
}