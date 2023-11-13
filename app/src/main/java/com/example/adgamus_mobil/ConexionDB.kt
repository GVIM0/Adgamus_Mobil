package com.example.adgamus_mobil

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class ConexionDB {

    fun Conectar() {
        val url = "jdbc:mysql://localhost:3306/Adgamus?autoReconnect=true&useSSL=false"
        val user = "root"
        val password = "n0m3l0"

        var connection: Connection? = null

        try {
            connection = DriverManager.getConnection(url, user, password)
            println("Conexión exitosa a la base de datos")
        } catch (e: SQLException) {
            println("Error al conectar a la base de datos: ${e.message}")
        } finally {
            connection?.close()
        }
    }

}