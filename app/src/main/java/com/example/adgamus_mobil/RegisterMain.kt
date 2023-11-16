package com.example.adgamus_mobil

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class RegisterMain : AppCompatActivity() {

    // Botones
    lateinit var Registrame: Button
    lateinit var Email: EditText
    lateinit var Nombre: EditText
    lateinit var Contraseña: EditText
    lateinit var Conf_Contraseña: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_main)

        // EditText
        Email = findViewById(R.id.Email_edit_log)
        Nombre = findViewById(R.id.Nombre_edit_log)
        Contraseña = findViewById(R.id.Contraseña_edit_log)
        Conf_Contraseña = findViewById(R.id.Conf_Contraseña_edit_log)

        // Botones
        Registrame = findViewById(R.id.Btn_Register)

        Registrame.setOnClickListener {

            // Verificación de existencia de correo en la base de datos
            val urlVerificacion = "http://192.168.101.11/Adgamus_Movil/Registro.php?CorreoUsuario=${Email.text.toString()}"

            val verificacionRequest = StringRequest(Request.Method.GET, urlVerificacion, { response ->
                    if (response.trim().equals("No hay registros", ignoreCase = true)) {
                        // El correo no existe en la base de datos, proceder con el registro

                        // Código de registro
                        if (EmailValido(Email.text.toString()) && Nombre.text.isNotBlank() && Contraseña.text.isNotBlank() && Conf_Contraseña.text.isNotBlank()) {

                            if (Contraseña.text.toString() == Conf_Contraseña.text.toString()) {

                                // URL Base de datos inserción
                                val urlRegistro = "http://192.168.101.11/Adgamus_Movil/Insertar.php"

                                val queue = Volley.newRequestQueue(this)

                                val resultadoPost = object : StringRequest(Request.Method.POST, urlRegistro, Response.Listener<String> { response ->
                                        Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_LONG).show()

                                // Cambio de actividad
                                val intento = Intent(this, LoginMain::class.java)
                                startActivity(intento)

                                Email.setText("")
                                Nombre.setText("")
                                Contraseña.setText("")
                                Conf_Contraseña.setText("")

                                }, Response.ErrorListener { error ->
                                        Toast.makeText(this, "Error en la conexión: $error", Toast.LENGTH_LONG).show()
                                }) {
                                    override fun getParams(): MutableMap<String, String> {
                                        val parametros = HashMap<String, String>()
                                        parametros["CorreoUsuario"] = Email.text.toString()
                                        parametros["NombreUsuario"] = Nombre.text.toString()
                                        parametros["Contraseña"] = Conf_Contraseña.text.toString()
                                        return parametros
                                    }
                                }
                                queue.add(resultadoPost)
                            } else {
                                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(this, "Llena todos los campos", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        // El correo ya existe en la base de datos, mostrar un mensaje de error
                        Toast.makeText(this, "El correo ya está registrado", Toast.LENGTH_SHORT).show()
                    }
                }, { error ->
                    Toast.makeText(this, "Error en la conexión: $error", Toast.LENGTH_LONG).show()
                })
            val queue = Volley.newRequestQueue(this)
            queue.add(verificacionRequest)

        }

    }
    private fun EmailValido(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        return emailRegex.matches(email)
    }
}

