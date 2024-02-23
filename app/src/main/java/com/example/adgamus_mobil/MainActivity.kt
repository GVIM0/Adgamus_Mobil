package com.example.adgamus_mobil

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var Comenzar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Busca el boton comenzar del inicio
        Comenzar = findViewById(R.id.Comenzar)

        Comenzar.setOnClickListener {
            val intento = Intent(this, Log_Sign_Main::class.java)
            startActivity(intento)
        }

    }


    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {

    }

}