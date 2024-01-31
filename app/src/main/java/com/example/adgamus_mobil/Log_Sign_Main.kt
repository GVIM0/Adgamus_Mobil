package com.example.adgamus_mobil

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class Log_Sign_Main : AppCompatActivity() {

    private var tabLayout: TabLayout? = null
    private var viewPager2: ViewPager2? = null
    private var adapter: ViewPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_sig_main)

        tabLayout = findViewById(R.id.tab_layout)
        viewPager2 = findViewById(R.id.ViewPager)

        tabLayout?.newTab()?.setText("Inicio")?.let { tabLayout?.addTab(it) }
        tabLayout?.newTab()?.setText("Registro")?.let { tabLayout?.addTab(it) }

        val fragmentManager = supportFragmentManager

        adapter = ViewPagerAdapter(fragmentManager, lifecycle)

        viewPager2?.adapter = adapter

        tabLayout?.addOnTabSelectedListener(object : OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager2?.setCurrentItem(tab.position, true)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        viewPager2?.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout?.selectTab(tabLayout?.getTabAt(position))
            }
        })
    }
}