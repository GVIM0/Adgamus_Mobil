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

        return binding.root
    }
    

}