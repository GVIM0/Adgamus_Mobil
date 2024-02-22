package com.example.adgamus_mobil

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.adgamus_mobil.databinding.MenuPrincipalBinding
import com.example.adgamus_mobil.fragments.AjustesFragment
import com.example.adgamus_mobil.fragments.AnimalesFragment
import com.example.adgamus_mobil.fragments.HomeFragment
import com.example.adgamus_mobil.fragments.MensajesFragment
import com.example.adgamus_mobil.fragments.PerfilFragment
import com.example.adgamus_mobil.fragments.PlantasFragment
import com.example.adgamus_mobil.fragments.RecursosFragment
import com.example.adgamus_mobil.fragments.RedFragment
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView

class Menu_Principal : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener,
    NavigationBarView.OnItemSelectedListener,
    HomeFragment.OnFragmentInteractionListener{

    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: MenuPrincipalBinding
    // Animaciones eliminadas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_principal)

        val homeFragment = HomeFragment()
        homeFragment.setFragmentInteractionListener(this)

        binding = MenuPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.nav_open, R.string.nav_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationDrawer.setNavigationItemSelectedListener(this)

        // Animaciones eliminadas

        //Selección del BottomNavigation
        binding.bottomNavigation.setOnItemSelectedListener{ item ->
            when (item.itemId) {
                R.id.bottom_home -> {
                    openFragment(HomeFragment())
                }
                R.id.bottom_Plantas -> openFragment(PlantasFragment())
                R.id.bottom_Animales -> openFragment(AnimalesFragment())
                R.id.bottom_recursos -> openFragment(RecursosFragment())
            }

            // Deseleccionar todos los elementos del NavigationView
            binding.navigationDrawer.menu.forEach { menuItem ->
                menuItem.isChecked = false
            }
            true
        }
        fragmentManager = supportFragmentManager
        openFragment(HomeFragment())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_Red_Social-> {
                openFragment(RedFragment())
                bottom_change_drawer()
            }
            R.id.nav_Mensajes -> {
                openFragment(MensajesFragment())
                bottom_change_drawer()
            }
            R.id.nav_Bot -> {
                Toast.makeText(this, "Chat bot", Toast.LENGTH_LONG).show()
                bottom_change_drawer()
            }
            R.id.nav_Ajustes -> {
                openFragment(AjustesFragment())
                bottom_change_drawer()
            }
            R.id.nav_Perfil -> {
                openFragment(PerfilFragment())
                bottom_change_drawer()
            }
            R.id.nav_logout -> {
                // Cambio de actividad
                val intento = Intent(this, Log_Sign_Main::class.java)
                startActivity(intento)
            }
        }

        // Animación eliminada

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    private fun openFragment(fragment: Fragment){
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

    override fun Entrada_Bot_Nav() {
        binding.bottomNavigation.visibility = View.VISIBLE
        // Animación eliminada
    }

    private fun bottom_change_drawer(){
        // Cambia el menú del BottomNavigationView
        binding.bottomNavigation.menu.clear()
        binding.bottomNavigation.inflateMenu(R.menu.bottom_menu_2)

        // Ajusta la posición del BottomNavigationView según sea necesario
        val layoutParams = binding.bottomNavigation.layoutParams as RelativeLayout.LayoutParams
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE)
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_START, 0)

        // Ajusta el tamaño al tamaño del botón
        layoutParams.width = 55.dpToPx() // Cambia 55 al tamaño deseado
        layoutParams.height = 55.dpToPx() // Cambia 55 al tamaño deseado

        binding.bottomNavigation.layoutParams = layoutParams
    }

    private fun bottom_change(){
        // Cambia el menú del BottomNavigationView
        binding.bottomNavigation.menu.clear()
        binding.bottomNavigation.inflateMenu(R.menu.bottom_menu)

        // Ajusta la posición del BottomNavigationView según sea necesario
        val layoutParams = binding.bottomNavigation.layoutParams as RelativeLayout.LayoutParams
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE)
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_START, 0)

        // Ajusta el tamaño al tamaño del botón
        layoutParams.width = 600.dpToPx() // Cambia 55 al tamaño deseado
        layoutParams.height = 55.dpToPx() // Cambia 55 al tamaño deseado

        binding.bottomNavigation.layoutParams = layoutParams
    }

    // Función de extensión para convertir dp a píxeles
    private fun Int.dpToPx(): Int {
        val density = resources.displayMetrics.density
        return (this * density).toInt()
    }
}

