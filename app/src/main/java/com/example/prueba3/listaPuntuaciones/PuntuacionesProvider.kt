package com.example.prueba3.listaPuntuaciones

import android.content.Context
import android.os.Environment
import com.example.prueba3.MainActivity
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVPrinter
import java.io.*
import java.nio.file.Files
import java.nio.file.Path

class PuntuacionesProvider() {


    companion object {

        val storageDir =
            File("/storage/emulated/0/Android/data/com.example.Prueba3/files/Documents")

        fun obtenerTodasLasPuntuaciones(): MutableList<Puntuacion> {

            val listaPuntuacion: MutableList<Puntuacion> = listOf<Puntuacion>().toMutableList()
            val ficheroCsv = File("${storageDir.path}/puntuaciones.csv")
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
            val ficheroCsv = File("${storageDir.path}/puntuaciones.csv")
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

            val ficheroCsv = File("${storageDir.path}/puntuaciones.csv")
            val ficheroExiste = ficheroCsv.exists()

            if (!ficheroExiste) {
                ficheroCsv.createNewFile()
            }
            val fileWriter = FileWriter(ficheroCsv, true)
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