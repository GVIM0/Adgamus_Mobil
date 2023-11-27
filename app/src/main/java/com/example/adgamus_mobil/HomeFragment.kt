package com.example.adgamus_mobil

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.adgamus_mobil.R
import com.example.adgamus_mobil.databinding.FragmentHomeBinding



class HomeFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null
    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Obtener el valor enviado desde el fragmento anterior
        val dato = arguments?.getString("clave")

        // Haz algo con el valor
        if (dato != null) {
            // Si hay un dato, ejecutar la función en el fragmento principal
            ejecutarFuncionEnPrincipal()
        }

        val imageViewToAjustes = binding.ToAjustes
        val imageViewToPerfil = binding.ToPerfil

        imageViewToAjustes.setOnClickListener {
            Cambiar_Fragmento(AjustesFragment(), "DesdeHome")
        }

        imageViewToPerfil.setOnClickListener {
            Cambiar_Fragmento(PerfilFragment(), "DesdeHome")
        }

        return binding.root
    }

    interface OnFragmentInteractionListener {
        fun Entrada_Bot_Nav()
    }

    fun setFragmentInteractionListener(listener: OnFragmentInteractionListener) {
        this.listener = listener
    }

    fun attachListener(listener: OnFragmentInteractionListener) {
        this.listener = listener
    }

    private fun ejecutarFuncionEnPrincipal() {
        listener?.Entrada_Bot_Nav()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }


    private fun Cambiar_Fragmento(fragment: Fragment, dato: String? = null) {
        // Si hay dato, agrégalo al fragmento
        if (dato != null) {
            fragment.arguments = Bundle().apply {
                putString("clave", dato)
            }
        }

        // Obtener el FragmentManager
        val fragmentManager = requireActivity().supportFragmentManager

        // Iniciar una transacción de fragmentos
        val transaction = fragmentManager.beginTransaction()

        // Reemplazar el fragmento actual con el nuevo fragmento
        transaction.replace(R.id.fragment_container, fragment)

        // Agregar la transacción a la pila de retroceso (opcional)
        transaction.addToBackStack(null)

        // Confirmar la transacción
        transaction.commit()
    }
}
