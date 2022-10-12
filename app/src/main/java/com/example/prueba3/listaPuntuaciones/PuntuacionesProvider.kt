package com.example.prueba3.listaPuntuaciones

import android.content.Context
import android.os.Environment
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVPrinter
import java.io.*
import java.nio.file.Files

class PuntuacionesProvider() {



    companion object {

        private lateinit var context: Context

        fun setContext(con: Context) {
            context=con
        }

        fun obtenerTodasLasPuntuaciones(): List<Puntuacion> {

            val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)

            val listaPuntuacion: MutableList<Puntuacion> = listOf<Puntuacion>().toMutableList()
            val ficheroCsv = File("$storageDir/puntuaciones.csv")
            val ficheroExiste = ficheroCsv.exists()

            if (!ficheroExiste) {
                ficheroCsv.createNewFile()
                return listaPuntuacion
            }

            val bufferedReader = BufferedReader(FileReader(ficheroCsv))
            val csvParser = CSVParser(bufferedReader, CSVFormat.DEFAULT);
            for (csvRecord in csvParser) {

                val nombre = csvRecord[0]
                val nombre_fichero = csvRecord[1]
                listaPuntuacion.add(Puntuacion(nombre, nombre_fichero))
            }
            return listaPuntuacion
        }

        fun escribirTodasLasPuntuaciones(puntuaciones: List<Puntuacion>) {

            val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)

            val ficheroCsv = File("$storageDir/puntuaciones.csv")
            val ficheroExiste = ficheroCsv.exists()

            if (!ficheroExiste) {
                ficheroCsv.createNewFile()
            }
            val fileWriter = FileWriter(ficheroCsv)
            val csvPrinter = CSVPrinter(fileWriter, CSVFormat.DEFAULT)
            if (!ficheroExiste) {
                csvPrinter.printRecord("Nombre", "Nombre_Fichero")
            }
            puntuaciones.forEach { (nombre, nombre_fichero) ->
                csvPrinter.printRecord(
                    nombre,
                    nombre_fichero
                )
            }
        }

        fun anadirCsv(puntuacion: Puntuacion) {

            val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
            val ficheroCsv = File("$storageDir/puntuaciones.csv")
            val ficheroExiste = ficheroCsv.exists()

            if (!ficheroExiste) {
                ficheroCsv.createNewFile()
            }
             val fileWriter = FileWriter(ficheroCsv,true)
            val csvPrinter = CSVPrinter(fileWriter, CSVFormat.EXCEL)
            if (!ficheroExiste) {
                csvPrinter.printRecord("Nombre", "Nombre_Fichero")
            }
            csvPrinter.printRecord(
                puntuacion.nombre,
                puntuacion.nombre_fichero
            )
            csvPrinter.flush()
            csvPrinter.close()
        }
    }
}