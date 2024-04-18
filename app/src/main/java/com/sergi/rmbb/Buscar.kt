package com.sergi.rmbb

/*class Buscar : AppCompatActivity() {
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
            val con = SQLite(this, "Dibujos", null, 1)
            val basedatos = con.readableDatabase
            val query = "SELECT * FROM personajes WHERE id=?"
            val cursor: Cursor? = basedatos.rawQuery(query, arrayOf(input))
            cursor?.use { c ->
                Log.d("Buscar", "Número de columnas en el cursor: ${c.columnCount}")
                for (i in 0 until c.columnCount) {
                    Log.d("Buscar", "Nombre de la columna $i: ${c.getColumnName(i)}")
                }
                if (c.moveToFirst()) {
                    val name = c.getString(c.getColumnIndex("name"))
                    val status = c.getString(c.getColumnIndex("status"))
                    val species = c.getString(c.getColumnIndex("species"))

                    Log.d("Buscar", "Nombre: $name, Estado: $status, Especie: $species")

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
}*/