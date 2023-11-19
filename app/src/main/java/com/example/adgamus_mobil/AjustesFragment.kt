package com.example.adgamus_mobil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.adgamus_mobil.databinding.MenuPrincipalBinding

class AjustesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ajustes, container, false)
        closeBottom()
    }

    private fun closeBottom(){
        lateinit var binding: MenuPrincipalBinding
        binding = MenuPrincipalBinding.inflate(layoutInflater)

        binding.bottomNavigation.background = null
    }

}