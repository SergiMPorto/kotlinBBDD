package com.sergi.rmbb

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class PersonajesAdapter(
    private val context: Context,
    private val personajesList: ArrayList<Personaje>
) : BaseAdapter() {

    override fun getCount(): Int {
        return personajesList.size
    }

    override fun getItem(position: Int): Any {
        return personajesList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.lista_item_personajes, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val personaje = personajesList[position]
        viewHolder.idTextView.text = personaje.id.toString()
        viewHolder.nameTextView.text = personaje.name
        viewHolder.statusTextView.text = personaje.status
        viewHolder.speciesTextView.text = personaje.species


        return view
    }

    fun actualizarPersonajes(nuevosPersonajes: List<Personaje>) {
        personajesList.clear()
        personajesList.addAll(nuevosPersonajes)
        notifyDataSetChanged()
    }

    private class ViewHolder(view: View) {
        val idTextView: TextView = view.findViewById(R.id.id)
        val nameTextView: TextView = view.findViewById(R.id.nombrelist)
        val statusTextView: TextView = view.findViewById(R.id.statuslist)
        val speciesTextView: TextView = view.findViewById(R.id.specieslist)

    }
}