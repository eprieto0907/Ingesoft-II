package com.inter.interapp.ui.localidades

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inter.interapp.data.model.LocalidadResponse
import com.inter.interapp.databinding.ItemLocalidadBinding

class LocalidadesAdapter(private var items: List<LocalidadResponse>) :
    RecyclerView.Adapter<LocalidadesAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemLocalidadBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLocalidadBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvAbreviacion.text = item.AbreviacionCiudad ?: "-"
        holder.binding.tvNombreCompleto.text = item.NombreCompleto ?: "Sin nombre"
    }

    override fun getItemCount() = items.size

    fun updateData(newItems: List<LocalidadResponse>) {
        items = newItems
        notifyDataSetChanged()
    }
}