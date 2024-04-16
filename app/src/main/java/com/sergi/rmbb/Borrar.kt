package com.sergi.rmbb

import RetrofitService
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Borrar : AppCompatActivity() {
    private lateinit var btnFind: Button
    private lateinit var idD: EditText
    private val apiService = RetrofitService.apiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_borrar)

        btnFind = findViewById(R.id.btnFind)
        idD = findViewById(R.id.idD)

        btnFind.setOnClickListener {
            val idString = idD.text.toString()
            if (idString.isNotEmpty()) {
                val id = idString.toInt()
                eliminarPersonaje(id) // Llamada al método eliminarPersonaje
            } else {
                Toast.makeText(this, "Ingrese un ID válido", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun eliminarPersonaje(id: Int) { // Cambio del nombre del método a eliminarPersonaje
        apiService.deleteCharacter(id).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        this@Borrar,
                        "Personaje eliminado exitosamente",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e("Borrar", "Personaje eliminado: $id")
                } else {
                    val errorMessage = "Error al eliminar el personaje: ${response.code()}"
                    Toast.makeText(this@Borrar, errorMessage, Toast.LENGTH_SHORT).show()
                    Log.e("BorrarError", errorMessage)
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                val errorMessage = "Error de red: ${t.message}"
                Toast.makeText(this@Borrar, errorMessage, Toast.LENGTH_SHORT).show()
                Log.e("BorrarError", errorMessage)
            }
        })
    }
}