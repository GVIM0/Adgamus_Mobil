package com.example.adgamus_mobil

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class LoginMain : AppCompatActivity() {

    // Botones
    lateinit var Olvidar: TextView
    lateinit var Login: Button

    // EditText
    lateinit var Email: EditText
    lateinit var Contraseña: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_main)

        // Botones
        Olvidar = findViewById(R.id.Olvidar_contraseña)
        Login = findViewById(R.id.Btn_Login)

        // EditText
        Email = findViewById(R.id.Email_edit_log)
        Contraseña = findViewById(R.id.Contraseña_edit_log)

        // Cambio a register
        Olvidar.setOnClickListener {
            val intento = Intent(this, RegisterMain::class.java)
            startActivity(intento)
        }

        // Inicio de sesion
        Login.setOnClickListener {

            // DB
            if(EmailValido(Email.text.toString()) && Contraseña.text.isNotBlank()){

            } else {
                Toast.makeText(this, "Llena todos los campos", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun EmailValido(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        return emailRegex.matches(email)
    }
}