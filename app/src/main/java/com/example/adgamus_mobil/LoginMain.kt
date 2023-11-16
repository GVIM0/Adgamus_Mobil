package com.example.adgamus_mobil

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

            // Verificación de existencia de correo en la base de datos
            val urlVerificacion = "http://192.168.101.11/Adgamus_Movil/Registro.php?CorreoUsuario=${Email.text.toString()}"
            // Código de registro
            if (EmailValido(Email.text.toString()) && Contraseña.text.isNotBlank()) {
                val verificacionRequest = StringRequest(Request.Method.GET, urlVerificacion, { response ->

                    if (response.trim().equals("No hay registros", ignoreCase = true)) {
                        // El correo no existe en la base de datos
                        Toast.makeText(this, "Registrate para acceder", Toast.LENGTH_SHORT).show()
                    } else {
                        // Cambio de actividad
                        val intento = Intent(this, Menu_Principal::class.java)
                        startActivity(intento)
                        Toast.makeText(this,"Bienvenid@ a Adgamus",Toast.LENGTH_LONG).show()
                        Email.setText("")
                        Contraseña.setText("")
                    }
                }, { error ->
                    Toast.makeText(this, "Error en la conexión: $error", Toast.LENGTH_LONG).show()
                })
                val queue = Volley.newRequestQueue(this)
                queue.add(verificacionRequest)
            } else {
                Toast.makeText(this, "Llena todos los campos", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun EmailValido(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        return emailRegex.matches(email)
    }

    private fun showErrorDialog() {
        val errorConstraintLayout: ConstraintLayout = findViewById(R.id.ErrorDialogLayout)
        val view = LayoutInflater.from(this).inflate(R.layout.error_dialog, errorConstraintLayout)
        val errorClose: Button? = view?.findViewById(R.id.errorClose)

        if (errorClose != null) {
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            val alertDialog: AlertDialog = builder.create()

            errorClose.setOnClickListener {
                alertDialog.dismiss()ss
                Toast.makeText(this, "Cerrado", Toast.LENGTH_LONG).show()
            }

            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            alertDialog.show()
        } else {
            Toast.makeText(this, "Error con el desplegado de la alerta", Toast.LENGTH_LONG).show()
        }

    }
}