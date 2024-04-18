package com.sergi.rmbb

/*
class Modificar : AppCompatActivity() {
    private lateinit var db: SQLite
    private lateinit var idM: EditText
    private lateinit var nombreM: EditText
    private lateinit var statusM: EditText
    private lateinit var speciesM: EditText
    private lateinit var btnM: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar)

        db = SQLite(this, "Dibujos", null, 1) // Reemplaza "Dibujos" con el nombre de tu base de datos

        idM = findViewById(R.id.idM)
        nombreM = findViewById(R.id.nombreM)
        statusM = findViewById(R.id.statusM)
        speciesM = findViewById(R.id.speciesM)
        btnM = findViewById(R.id.btnM)

        btnM.setOnClickListener {
            modificar()
        }
    }

    private fun modificar() {
        val id = idM.text.toString()
        val nombre = nombreM.text.toString()
        val status = statusM.text.toString()
        val species = speciesM.text.toString()

        if (id.isNotEmpty() && nombre.isNotEmpty() && status.isNotEmpty() && species.isNotEmpty()) {

            val idInt = id.toInt()
            if (db.verificarExistencia(idInt)) {

                val filasAfectadas = db.modificarPersonaje(idInt, nombre, status, species)
                if (filasAfectadas > 0) {
                    Toast.makeText(this, "Registro modificado exitosamente", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error al modificar registro", Toast.LENGTH_SHORT).show()
                }
            } else {

                Toast.makeText(this, "El registro con ID $id no existe", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
        }
    }
}
*/