package com.example.adgamus_mobil

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.adgamus_mobil.fragments.LoginTabFragment
import com.example.adgamus_mobil.fragments.SingupTabFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun createFragment(position: Int): Fragment {
        return if (position == 1) {
            SingupTabFragment()
        } else LoginTabFragment()
    }

    override fun getItemCount(): Int {
        return 2
    }
}
