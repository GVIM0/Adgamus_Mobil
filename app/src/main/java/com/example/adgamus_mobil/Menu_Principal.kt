package com.example.adgamus_mobil

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.adgamus_mobil.databinding.MenuPrincipalBinding
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView

class Menu_Principal : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, NavigationBarView.OnItemSelectedListener {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: MenuPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_principal)

        binding = MenuPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.nav_open, R.string.nav_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationDrawer.setNavigationItemSelectedListener(this)

        // Quita el fondo (me quito 3 horas de vida y 5-6 videos ademas de consultas a chatGPT para encontrarlo)
        // binding.bottomNavigation.background = null

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.bottom_home -> openFragment(HomeFragment())
                R.id.bottom_Plantas -> openFragment(PlantasFragment())
                R.id.bottom_Animales -> openFragment(AnimalesFragment())
                R.id.bottom_recursos -> openFragment(RecursosFragment())
            }
            true
        }
        fragmentManager = supportFragmentManager
        openFragment(HomeFragment())

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            // Agregar fragmentos como en los botones de abajo
            R.id.nav_Red_Social-> openFragment(RedFragment())
            R.id.nav_Mensajes -> openFragment(MensajesFragment())
            R.id.nav_Bot -> Toast.makeText(this, "Chat bot", Toast.LENGTH_LONG).show()
            R.id.nav_Ajustes -> openFragment(AjustesFragment())
            R.id.nav_Perfil -> openFragment(PerfilFragment())
            R.id.nav_logout -> {
                // Cambio de actividad
                val intento = Intent(this, LoginMain::class.java)
                startActivity(intento)
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true

    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else{

        }
    }

    private fun openFragment(fragment: Fragment){
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }


}