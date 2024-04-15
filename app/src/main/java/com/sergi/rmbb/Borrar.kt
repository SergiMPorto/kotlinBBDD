package com.sergi.rmbb

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Borrar : AppCompatActivity() {
    private lateinit var db: SQLite
    private lateinit var btnFind: Button
    private lateinit var nameD: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_borrar)

        db = SQLite(this, "Dibujos", null, 1)


        btnFind = findViewById(R.id.btnFind)
        nameD = findViewById(R.id.nameD)

        btnFind.setOnClickListener {
            val nombre = nameD.text.toString()
            val existe = db.verificarExistencia(nombre)
            if (existe) {
                val filasAfectadas = db.eliminarPersonaje(nombre)
                if (filasAfectadas > 0) {
                    Toast.makeText(this, "Eliminado exitosamente", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error al eliminar", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "El personaje no existe", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
