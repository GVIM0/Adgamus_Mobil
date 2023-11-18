package com.example.adgamus_mobil

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.lifecycle.ReportFragment.Companion.reportFragment
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

            if (EmailValido(Email.text.toString()) && Contraseña.text.isNotBlank()) {
                // Verificación de existencia de correo en la base de datos
                val urlVerificacion = "http://192.168.101.11/Adgamus_Movil/Login.php?CorreoUsuario=${Email.text.toString()}&Contraseña=${Contraseña.text.toString()}"
                val verificacionRequest = StringRequest(Request.Method.GET, urlVerificacion, { response ->

                    if (response.trim().equals("No hay registros", ignoreCase = true)) {
                        showErrorDialog("Inicio de sesion","Registrate como nuevo usuario o verifica tu coreo u contraseña")
                    }  else {
                        showDialog("Un nuevo comienzo", "Bienvenido a Adgamus")
                    }
                    Email.setText("")
                    Contraseña.setText("")

                }, { error ->
                    Toast.makeText(this, "Error en la conexión: $error", Toast.LENGTH_LONG).show()
                })
                val queue = Volley.newRequestQueue(this)
                queue.add(verificacionRequest)
            } else {
                showErrorDialog("Llenado de campos","Completa todos los campos antes de iniciar sesion")
            }

        }

    }

    private fun EmailValido(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        return emailRegex.matches(email)
    }

    private fun showErrorDialog(message1: String,message: String) {
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.error_dialog, null)
        val textError: TextView = view.findViewById(R.id.ErrorTitle)
        textError.text = message1 // Establece el titulo del mensaje
        val textViewError: TextView = view.findViewById(R.id.errorDesc)
        textViewError.text = message // Establece el texto del diálogo

        val builder = AlertDialog.Builder(this)
        builder.setView(view)
        val alertDialog = builder.create()

        val errorClose: Button = view.findViewById(R.id.errorClose)
        errorClose.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.show()
    }

    private fun showDialog(message1: String, message: String) {
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.alert_dialog, null)
        val text: TextView = view.findViewById(R.id.Title)
        text.text = message1 // Establece el titulo del mensaje
        val textViewError: TextView = view.findViewById(R.id.Descrip)
        textViewError.text = message // Establece el texto del diálogo

        val builder = AlertDialog.Builder(this)
        builder.setView(view)
        val alertDialog = builder.create()

        val errorClose: Button = view.findViewById(R.id.HapClose)
        errorClose.setOnClickListener {
            // Cambio de actividad
            val intento = Intent(this, Menu_Principal::class.java)
            startActivity(intento)
            alertDialog.dismiss()
        }

        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.show()
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {

    }


}