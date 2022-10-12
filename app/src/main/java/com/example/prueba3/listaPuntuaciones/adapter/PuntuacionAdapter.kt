package com.example.prueba3.listaPuntuaciones.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.prueba3.R
import com.example.prueba3.listaPuntuaciones.Puntuacion

class PuntuacionAdapter(val listaPuntuacion: List<Puntuacion>):RecyclerView.Adapter<PuntuacionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PuntuacionViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
        return PuntuacionViewHolder(layoutInflater.inflate(R.layout.item_lista,parent, false))
    }

    override fun onBindViewHolder(holder: PuntuacionViewHolder, position: Int) {
    val item = listaPuntuacion[position]
        holder.render(item,)
    }

    override fun getItemCount(): Int {
        return listaPuntuacion.size

    }
}