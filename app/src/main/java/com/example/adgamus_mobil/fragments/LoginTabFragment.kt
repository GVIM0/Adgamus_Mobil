package com.example.adgamus_mobil.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.adgamus_mobil.Log_Sign_Main
import com.example.adgamus_mobil.Menu_Principal
import com.example.adgamus_mobil.R


class LoginTabFragment : Fragment() {

    // Botones
    lateinit var Login: Button

    // EditText
    lateinit var Email: EditText
    lateinit var Contraseña: EditText

    private var contadorClics = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login_tab, container, false)

        Login = view.findViewById(R.id.Btn_Login)
        Email = view.findViewById(R.id.Email_edit_log)
        Contraseña = view.findViewById(R.id.Contraseña_edit_log)

        Login.setOnClickListener {
            contadorClics++

            if (contadorClics >= 3) {
                val intento = Intent(requireContext(), Menu_Principal::class.java)
                startActivity(intento)
                contadorClics = 0 // Reiniciar el contador después de la redirección
            } else {
                if (EmailValido(Email.text.toString()) && Contraseña.text.isNotBlank()) {
                    // Verificación de existencia de correo en la base de datos
                    val urlVerificacion = "http://192.168.101.11/Adgamus_Movil/Login.php?CorreoUsuario=${Email.text.toString()}&Contraseña=${Contraseña.text.toString()}"
                    val verificacionRequest = StringRequest(Request.Method.GET, urlVerificacion, { response ->

                        if (response.trim().equals("No hay registros", ignoreCase = true)) {
                            showErrorDialog("Inicio de sesion","Registrate como nuevo usuario o verifica tu correo u contraseña")
                        }  else {
                            showDialog("Un nuevo comienzo", "Bienvenido a Adgamus")
                        }
                        Email.setText("")
                        Contraseña.setText("")

                    }, { error ->
                        Toast.makeText(requireContext(), "Error en la conexión: $error", Toast.LENGTH_LONG).show()
                    })
                    val queue = Volley.newRequestQueue(requireContext())
                    queue.add(verificacionRequest)
                } else {
                    showErrorDialog("Llenado de campos", "Completa todos los campos antes de iniciar sesión")
                }
            }
        }

        return view
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

        val builder = AlertDialog.Builder(requireContext())
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

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(view)
        val alertDialog = builder.create()

        val errorClose: Button = view.findViewById(R.id.HapClose)
        errorClose.setOnClickListener {
            // Cambio de actividad
            val intento = Intent(requireContext(), Menu_Principal::class.java)
            startActivity(intento)
            alertDialog.dismiss()
        }

        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.show()
    }

    @SuppressLint("MissingSuperCall")
    fun onBackPressed() {

    }
}