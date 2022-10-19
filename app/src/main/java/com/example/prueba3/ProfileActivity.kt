package com.example.prueba3

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.prueba3.databinding.ActivityProfileBinding
import com.example.prueba3.listaPuntuaciones.NOMBRE
import com.example.prueba3.listaPuntuaciones.RUTA_IAMGEN
import com.squareup.picasso.Picasso
import java.io.File

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nombre = intent.getStringExtra(NOMBRE)
        val ruta_iamgen = intent.getStringExtra(RUTA_IAMGEN)
        binding.nombreUsuario.setText(nombre)

        val imgFile =
            File("/storage/emulated/0/Android/data/com.example.Prueba3/files/Pictures/${ruta_iamgen}")
        if (imgFile.exists()) {

            val uriIamgen = Uri.fromFile(imgFile)
            Picasso.with(binding.imagenPerfil.context).load(uriIamgen)
                .fit().centerCrop().into(binding.imagenPerfil)
        }
    }
}