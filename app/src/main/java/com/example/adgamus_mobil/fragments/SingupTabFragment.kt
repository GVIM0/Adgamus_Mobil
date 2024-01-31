package com.example.adgamus_mobil.fragments

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
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.adgamus_mobil.Menu_Principal
import com.example.adgamus_mobil.R

class SingupTabFragment : Fragment() {

    // Botones
    lateinit var Registrame: Button
    lateinit var Email: EditText
    lateinit var Contraseña: EditText
    lateinit var Conf_Contraseña: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_singup_tab, container, false)

        // EditText
        Email = view.findViewById(R.id.Email_edit_log)
        Contraseña = view.findViewById(R.id.Contraseña_edit_log)
        Conf_Contraseña = view.findViewById(R.id.Conf_Contraseña_edit_log)

        // Botones
        Registrame = view.findViewById(R.id.Btn_Register)

        Registrame.setOnClickListener {
            if (EmailValido(Email.text.toString()) && Contraseña.text.isNotBlank() && Conf_Contraseña.text.isNotBlank()) {
                if (Contraseña.text.toString() == Conf_Contraseña.text.toString()) {
                    // Verificación de existencia de correo en la base de datos
                    val urlVerificacion = "http://192.168.101.11/Adgamus_Movil/Registro.php?CorreoUsuario=${Email.text.toString()}"
                    val verificacionRequest = StringRequest(Request.Method.GET, urlVerificacion, { response ->
                        if (response.trim().equals("No hay registros", ignoreCase = true)) {
                            // El correo no existe en la base de datos, proceder con el registro

                            // URL Base de datos inserción
                            val urlRegistro = "http://192.168.101.11/Adgamus_Movil/Insertar.php"

                            val queue = Volley.newRequestQueue(requireContext())

                            val resultadoPost = object : StringRequest(Method.POST, urlRegistro, Response.Listener<String> { response ->
                                showDialog("Bienvenido", "Usuario registrado exitosamente")
                                Email.setText("")
                                Contraseña.setText("")
                                Conf_Contraseña.setText("")
                            }, Response.ErrorListener { error ->
                                Toast.makeText(requireContext(), "Error en la conexión: $error", Toast.LENGTH_LONG).show()
                            }) {
                                override fun getParams(): MutableMap<String, String> {
                                    val parametros = HashMap<String, String>()
                                    parametros["CorreoUsuario"] = Email.text.toString()
                                    parametros["Contraseña"] = Conf_Contraseña.text.toString()
                                    return parametros
                                }
                            }
                            queue.add(resultadoPost)
                        } else {
                            // El correo ya existe en la base de datos, mostrar un mensaje de error
                            showErrorDialog("Correo electronico", "El correo ya fue registrado, intenta con uno diferente")
                        }
                    }, { error ->
                        Toast.makeText(requireContext(), "Error en la conexión: $error", Toast.LENGTH_LONG).show()
                    })
                    val queue = Volley.newRequestQueue(requireContext())
                    queue.add(verificacionRequest)
                } else {
                    if (esLongitudContraseñaValida(Contraseña.text.toString(),Conf_Contraseña.text.toString())) {
                        // Continúa con el proceso de registro
                    } else {
                        showErrorDialog("Longitud de contraseña", "La contraseña No coinciden y deben tener entre 8 y 16 caracteres.")
                    }
                }
            } else {
                showErrorDialog("Llenado de campos", "Completa todos los campos"
                )
            }
        }
        return view
    }

    private fun EmailValido(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        return emailRegex.matches(email)
    }

    private fun showErrorDialog(message1: String, message: String) {
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
            intento.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intento)
            alertDialog.dismiss()
        }

        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.show()
    }

    private fun esLongitudContraseñaValida(contrasena: String, config: String): Boolean {
        return contrasena.length in 8..16
    }
}