package dev.whitedog.ejemplofoldable.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.whitedog.ejemplofoldable.R

class ElementoAdapter: RecyclerView.Adapter<ElementoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_elemento, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return 20
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(position: Int) {
            val texto: String = "Elemento $position"

            itemView.findViewById<TextView>(R.id.tv_adapter_elemento).text = texto
        }

    }
}