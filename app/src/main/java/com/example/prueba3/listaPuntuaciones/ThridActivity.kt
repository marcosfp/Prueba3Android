package com.example.prueba3.listaPuntuaciones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prueba3.R
import com.example.prueba3.listaPuntuaciones.PuntuacionesProvider.Companion.obtenerTodasLasPuntuaciones
import com.example.prueba3.listaPuntuaciones.adapter.PuntuacionAdapter

class ThridActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thrid)
        initRecyclerView()
    }


    fun initRecyclerView(){
        var recyclerView= findViewById<RecyclerView>(R.id.listaPuntuaciones)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val puntuacionesProvider = PuntuacionesProvider
            puntuacionesProvider.setContext(this)
        val listaPuntuaciones = puntuacionesProvider.obtenerTodasLasPuntuaciones()
        recyclerView.adapter= PuntuacionAdapter(listaPuntuaciones)
    }


}