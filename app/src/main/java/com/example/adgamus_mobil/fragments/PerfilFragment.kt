package com.example.adgamus_mobil.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.adgamus_mobil.Menu_Principal
import com.example.adgamus_mobil.R
import com.example.adgamus_mobil.databinding.FragmentPerfilBinding

class PerfilFragment : Fragment() {

    private lateinit var binding: FragmentPerfilBinding
    private lateinit var salida : Animation


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflar y obtener el binding
        binding = FragmentPerfilBinding.inflate(inflater, container, false)

        val imageViewToHome = binding.ToHome
        val imageViewToAjustes = binding.ToAjustes

        imageViewToHome.setOnClickListener {
            Cambiar_Fragmento(HomeFragment(), "Entrada")
        }

        imageViewToAjustes.setOnClickListener {
            Cambiar_Fragmento(AjustesFragment())
        }


        // Acceder a la variable de la actividad para verificar si la animación se ha ejecutado
        if ((requireActivity() as Menu_Principal).animacionEjecutada) {
            // La animación ya se ejecutó, establecer la visibilidad como permanente
            binding.ToHome.visibility = View.VISIBLE
        } else {
            // La animación no se ha ejecutado, configurar y ejecutar la animación
            val handler = Handler(Looper.getMainLooper())
            salida = AnimationUtils.loadAnimation(requireActivity(), R.anim.salida_img)

            handler.postDelayed({
                if (binding.ToHome.visibility == View.INVISIBLE) {
                    binding.ToHome.visibility = View.VISIBLE
                    binding.ToHome.startAnimation(salida)

                    salida.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation?) {}

                        override fun onAnimationEnd(animation: Animation?) {
                            binding.ToHome.visibility = View.VISIBLE
                            // Marcar que la animación se ha ejecutado en la actividad
                            (requireActivity() as Menu_Principal).animacionEjecutada = true
                        }

                        override fun onAnimationRepeat(animation: Animation?) {}
                    })
                }
            }, 1300)}

        return binding.root
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