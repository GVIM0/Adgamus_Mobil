package com.example.adgamus_mobil.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.adgamus_mobil.animales.Busqueda_Ganado
import com.example.adgamus_mobil.animales.Cruza_Ganado
import com.example.adgamus_mobil.animales.Historial_Ganado
import com.example.adgamus_mobil.R
import com.example.adgamus_mobil.animales.Seguimientos_Ganado
import com.example.adgamus_mobil.databinding.FragmentAnimalesBinding


class AnimalesFragment : Fragment() {

    private lateinit var binding: FragmentAnimalesBinding
    private lateinit var entrada : Animation

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        binding = FragmentAnimalesBinding.inflate(inflater, container, false)

        //Inicializamos las Imágenes

        val IV_Bus_Gan  = binding.ToBusquedaGanado
        val IV_Seg_Gan = binding.ToSeguimientoGanado
        val IV_Cruza = binding.ToCruza
        val IV_Historial = binding.ToHistorialGanado

        entrada = AnimationUtils.loadAnimation(requireActivity(), R.anim.entrada_img)

        IV_Bus_Gan.startAnimation(entrada)
        IV_Seg_Gan.startAnimation(entrada)
        IV_Cruza.startAnimation(entrada)
        IV_Historial.startAnimation(entrada)

        IV_Bus_Gan.setOnClickListener {
            Cambiar_Fragmento(Busqueda_Ganado())
        }
        IV_Seg_Gan.setOnClickListener {
            Cambiar_Fragmento(Seguimientos_Ganado())
        }
        IV_Cruza.setOnClickListener {
            Cambiar_Fragmento(Cruza_Ganado())
        }
        IV_Historial.setOnClickListener {
            Cambiar_Fragmento(Historial_Ganado())
        }




        return binding.root
    }

    private fun Cambiar_Fragmento(fragment: Fragment) {

        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()

        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}