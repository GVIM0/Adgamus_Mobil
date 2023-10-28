package com.example.adgamus_mobil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class RegisterMain : AppCompatActivity() {

    lateinit var Registrame: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_main)

        // Busca el boton comenzar del inicio
        Registrame = findViewById(R.id.Btn_Register)

        Registrame.setOnClickListener {
            val intento = Intent(this, LoginMain::class.java)
            startActivity(intento)
        }

    }
}