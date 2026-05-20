package com.inter.interapp.ui.tablas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inter.interapp.data.model.TablaSchema
import com.inter.interapp.databinding.ItemTablaBinding

class TablasAdapter(private var items: List<TablaSchema>) :
    RecyclerView.Adapter<TablasAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemTablaBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTablaBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvNombreTabla.text = item.NombreTabla ?: "Sin nombre"
        holder.binding.tvDescripcion.text = item.Descripcion ?: ""
        holder.binding.tvCantidad.text = "Registros: ${item.CantidadRegistros ?: 0}"
    }

    override fun getItemCount() = items.size

    fun updateData(newItems: List<TablaSchema>) {
        items = newItems
        notifyDataSetChanged()
    }
}