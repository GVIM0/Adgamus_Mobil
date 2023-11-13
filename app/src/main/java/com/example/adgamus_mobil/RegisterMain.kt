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

    // DB
    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_main)

        // DB
        sharedPreferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)

        // EditText
        Email = findViewById(R.id.Email_edit_log)
        Nombre = findViewById(R.id.Nombre_edit_log)
        Contraseña = findViewById(R.id.Contraseña_edit_log)
        Conf_Contraseña = findViewById(R.id.Conf_Contraseña_edit_log)

        // Botones
        Registrame = findViewById(R.id.Btn_Register)

        Registrame.setOnClickListener {

            // Datos DB
            if(EmailValido(Email.text.toString()) && Nombre.text.isNotBlank() && Contraseña.text.isNotBlank() && Conf_Contraseña.text.isNotBlank()) {
                if (Contraseña.text.toString() == Conf_Contraseña.text.toString()) {
                    val url = "http://192.168.1.117/Proyecto_Corluss/insertar.php"
                    val queue = Volley.newRequestQueue(this)
                    val resultadoPost = object: StringRequest(Request.Method.POST, url,
                        Response.Listener<String> { response ->
                            when {
                                response.contains("Nombre de usuario o correo ya registrado") -> {
                                    Toast.makeText(this, "Nombre de usuario o correo electrónico ya registrado", Toast.LENGTH_LONG).show()
                                }

                                response.contains("El usuario se registró de forma exitosa") -> {
                                    saveUserData(
                                        Email.text.toString(),
                                        Nombre.text.toString(),
                                        Conf_Contraseña.text.toString()
                                    )

                                    // Cambio de actividad
                                    val intento = Intent(this, LoginMain::class.java)
                                    startActivity(intento)
                                }

                                else -> {
                                    Toast.makeText(this, "Error: $response", Toast.LENGTH_LONG).show()
                                }
                            }
                        }, Response.ErrorListener { error ->
                            Toast.makeText(this, "Error en la conexión: $error", Toast.LENGTH_LONG)
                                .show()
                        }
                    ) {
                        override fun getParams(): MutableMap<String, String>? {
                            val parametros = HashMap<String, String>()
                            parametros.put("Correo", Email.text.toString())
                            parametros.put("Nombre", Nombre.text.toString())
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
        }

    }

    private fun EmailValido(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        return emailRegex.matches(email)
    }

    private fun saveUserData(Email: String, Nombre: String, Contraseña: String) {
        val editor = sharedPreferences.edit()
        editor.putString("CorreoUsuario", Email)
        editor.putString("NombreUsuario", Nombre)
        editor.putString("Contraseña", Contraseña)
        editor.apply()
    }

}
