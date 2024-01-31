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

        entrada = AnimationUtils.loadAnimation(requireActivity(), R.anim.entrada_img)

        return binding.root
    }

}