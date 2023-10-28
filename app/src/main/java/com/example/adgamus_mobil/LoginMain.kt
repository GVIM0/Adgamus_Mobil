package com.example.adgamus_mobil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LoginMain : AppCompatActivity() {

    lateinit var Olvidar: TextView
    lateinit var Login: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_main)

        // Busca el boton comenzar del inicio
        Olvidar = findViewById(R.id.Olvidar_contraseña)
        Login = findViewById(R.id.Btn_Login)

        Olvidar.setOnClickListener {
            val intento = Intent(this, RegisterMain::class.java)
            startActivity(intento)
        }

        Login.setOnClickListener {
            val intento = Intent(this, Menu_Principal::class.java)
            startActivity(intento)
        }

    }
}