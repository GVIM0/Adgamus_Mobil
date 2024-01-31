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
import com.example.adgamus_mobil.databinding.FragmentAjustesBinding

class AjustesFragment : Fragment(){


    private lateinit var binding: FragmentAjustesBinding
    private lateinit var salida : Animation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        // Inflar y obtener el binding
        binding = FragmentAjustesBinding.inflate(inflater, container, false)

        return binding.root
    }

}