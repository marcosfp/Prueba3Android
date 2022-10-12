package com.example.prueba3.listaPuntuaciones.adapter

import android.content.Context
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.prueba3.R
import com.example.prueba3.listaPuntuaciones.Puntuacion
import com.squareup.picasso.Picasso
import java.io.File

class PuntuacionViewHolder (view:View): RecyclerView.ViewHolder(view) {

    val imageView = view.findViewById<ImageView>(R.id.imgPuntuacion)
    val textView = view.findViewById<TextView>(R.id.textoPuntuacion)

    fun render(puntuacion: Puntuacion){

        textView.text=puntuacion.nombre

        val imgFile = File("/storage/emulated/0/Android/data/com.example.Prueba3/files/Pictures/${puntuacion.nombre_fichero}")
        if (imgFile.exists()) {

            val uriIamgen = Uri.fromFile(imgFile)
            Picasso.with(imageView.context).load(uriIamgen)
                .fit().centerCrop().into(imageView)
        }
    }

}