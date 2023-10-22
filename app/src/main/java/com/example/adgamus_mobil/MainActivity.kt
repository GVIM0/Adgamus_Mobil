package com.example.adgamus_mobil

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
            val intento = Intent(this, Menu_Principal::class.java)
            startActivity(intento)
        }
    }

}