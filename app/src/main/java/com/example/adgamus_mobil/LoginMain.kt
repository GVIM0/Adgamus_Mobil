package com.example.adgamus_mobil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginMain : AppCompatActivity() {

    lateinit var Login: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_main)

        // Busca el boton comenzar del inicio
        Login = findViewById(R.id.Btn_Login)


        Login.setOnClickListener {
            val intento = Intent(this, Menu_Principal::class.java)
            startActivity(intento)
        }

    }
}