package com.example.prueba3

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.AlarmClock
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider.getUriForFile
import com.example.prueba3.listaPuntuaciones.Puntuacion
import com.example.prueba3.listaPuntuaciones.PuntuacionesProvider
import com.example.prueba3.listaPuntuaciones.ThridActivity
import com.squareup.picasso.Picasso
import java.io.File
import java.io.IOException


class SecondActivity : AppCompatActivity() {

    private var uri: Uri? = null
    lateinit var currentPhotoPath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val message = intent.getStringExtra(EXTRA_MESSAGE)

        findViewById<TextView>(R.id.textoNombre).apply {
            text = message
        }

        val botonHacerFoto = findViewById<Button>(R.id.buttonFoto)
        botonHacerFoto.setOnClickListener { hacerFoto() }

        val listarPuntuaciones = Intent(this, ThridActivity::class.java).apply {
        }
        val botonListarPuntuaciones = findViewById<Button>(R.id.buttonListarPuntuaciones)
        botonListarPuntuaciones.setOnClickListener { startActivity(listarPuntuaciones) }

    }

    fun createAlarm(view: View) {
        val message = "prueba"
        var hour = 9
        val minutes = 30
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
            putExtra(AlarmClock.EXTRA_HOUR, hour)
            putExtra(AlarmClock.EXTRA_MINUTES, minutes)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }


    fun hacerFoto() {

        val photoFile: File? = try {
            crearFicheroImagen()
        } catch (ex: IOException) {
            // Error occurred while creating the File
            ex.printStackTrace()
            null
        }
        if (photoFile != null) {
            uri = getUriForFile(
                this,
                "com.example.prueba3.fileprovider",
                photoFile
            )
            obtenerCamara.launch(uri)
        }
    }

    var obtenerCamara =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                Log.i(TAG, "Image Location :$uri")
                recargarImageView(uri)

                val textView = findViewById<TextView>(R.id.textoNombre)
                val nombre = textView.text.toString()
                PuntuacionesProvider.anadirCsv(Puntuacion(nombre,uri!!.lastPathSegment!!))

            } else {
                Log.i(TAG, "Image not saved:$uri")
            }
        }

    private fun crearFicheroImagen(): File {
        val textView = findViewById<TextView>(R.id.textoNombre)
        val nombre = textView.text
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "FNL_${nombre}_",
            ".jpg",
            storageDir /* directory */
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

    private fun recargarImageView(uri: Uri?) {

        val archivo = uri!!.lastPathSegment
        val imgFile =
            File("/storage/emulated/0/Android/data/com.example.Prueba3/files/Pictures/$archivo")
        val imageView = findViewById<ImageView>(R.id.imageView)
        if (imgFile.exists()) {

            val uriImagen = Uri.fromFile(imgFile)

            Picasso.with(this).load(uriImagen)
                .fit().centerCrop().into(imageView)
        }
    }
}




