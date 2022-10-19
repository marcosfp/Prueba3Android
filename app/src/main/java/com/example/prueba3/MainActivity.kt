package com.example.prueba3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult.*
import com.example.prueba3.EscribirNombreActivity.Companion.NOMBREPARAGUARDAR
import com.example.prueba3.databinding.ActivityEscribirNombreBinding
import com.example.prueba3.databinding.ActivityMainBinding
import com.example.prueba3.listaPuntuaciones.ThridActivity


const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var message: String


    //Establecesmos las respuesta al lanzr la actiididad para que el usarios escriva el nombre
    private val respuestaNombreLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == RESULT_OK) {
                message= activityResult.data?.getStringExtra(NOMBREPARAGUARDAR).orEmpty()

                Toast.makeText(
                    this,
                    "tu nombre $message se ha guardado correctamente",
                    Toast.LENGTH_LONG
                ).show()

                binding.buttonFoto.alpha=1f
                binding.buttonFoto.setOnClickListener{sendMessage()}
            } else {
                Toast.makeText(this, "No se ha guardado el nombre", Toast.LENGTH_LONG).show()
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonListarUsuarios.setOnClickListener { listarUsuariosActivity() };
        binding.buttonFoto.setOnClickListener(null)
        binding.buttonFoto.alpha=.5f
        binding.buttonObtenerNombre.setOnClickListener {
            val intentobtenerNombre = Intent(this, EscribirNombreActivity::class.java)
            respuestaNombreLauncher.launch(intentobtenerNombre)
        }
    }

    //Carga el activity que toma la foto
    fun sendMessage() {
        val intent = Intent(this, SecondActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }


    //Carga la actividad para mostrar el recyclerview de los usuarios
    fun listarUsuariosActivity() {
        val thridActivity = Intent(this, ThridActivity::class.java)
        startActivity(thridActivity)

    }

}