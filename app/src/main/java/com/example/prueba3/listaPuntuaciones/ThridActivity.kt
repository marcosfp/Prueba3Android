package com.example.prueba3.listaPuntuaciones

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.prueba3.ProfileActivity
import com.example.prueba3.databinding.ActivityThridBinding
import com.example.prueba3.listaPuntuaciones.adapter.PuntuacionAdapter

const val NOMBRE = "com.exmaple.prueba3.thridactivity.nombre"
const val RUTA_IAMGEN = "com.exmaple.prueba3.thridactivity.ruta_imagen"

class ThridActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThridBinding
    private var listaPuntuaciones: MutableList<Puntuacion> =
        PuntuacionesProvider.obtenerTodasLasPuntuaciones()
    private lateinit var puntuacionAdapater: PuntuacionAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThridBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }


    fun initRecyclerView() {
        //Declaramos el adapatador del recyclerview
        puntuacionAdapater = PuntuacionAdapter(
            listaPuntuacion = listaPuntuaciones,
            onClickListener = { puntuacion -> selecionarElemento(puntuacion) },
            onClickDelete = { posicion -> borrarElemento(posicion) })
        //Establecemos su layoutmanager y el adaptador
        binding.listaPuntuacionesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.listaPuntuacionesRecyclerView.adapter = puntuacionAdapater

    }

    //Funcion que permita borrar un elemento en funcion de su posicion
    fun borrarElemento(posicion: Int) {
        listaPuntuaciones.removeAt(posicion)
        puntuacionAdapater.notifyDataSetChanged()
    }

    //Funcion que se ejecuta cada vez que se toca en un elemento de la lista
    fun selecionarElemento(puntuacion: Puntuacion) {
        Toast.makeText(this, "El elemento seleccionado es ${puntuacion.nombre} ", Toast.LENGTH_LONG)
            .show()

        val intent = Intent(this, ProfileActivity::class.java).apply {
            putExtra(NOMBRE, puntuacion.nombre)
            putExtra(RUTA_IAMGEN, puntuacion.nombre_fichero)
        }
        startActivity(intent)

    }


}