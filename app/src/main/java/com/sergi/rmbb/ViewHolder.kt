package com.sergi.rmbb

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val idTextView: TextView = itemView.findViewById(R.id.id)
    val nombreTextView: TextView = itemView.findViewById(R.id.nombrelist)
    val statusTextView: TextView = itemView.findViewById(R.id.statuslist)
    val speciesTextView: TextView = itemView.findViewById(R.id.specieslist)
}
