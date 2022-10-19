package com.example.prueba3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.prueba3.databinding.ActivityEscribirNombreBinding

class EscribirNombreActivity : AppCompatActivity() {

    //Declaramos una constante para recupear los valores del intent
    companion object{
        const val NOMBREPARAGUARDAR ="nombre"
    }

    private lateinit var binding: ActivityEscribirNombreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEscribirNombreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonGuardarNombre.setOnClickListener { guardar() }
    }
    //Funcion que manda de vuelta a la actividad main el nombre de usuario
    fun guardar() {
        var respuesta:String = binding.textViewIntroducirNombre.text.toString()
        val intent = Intent()
        intent.putExtra(NOMBREPARAGUARDAR, respuesta)

        setResult(RESULT_OK,intent)
        finish()

    }


}