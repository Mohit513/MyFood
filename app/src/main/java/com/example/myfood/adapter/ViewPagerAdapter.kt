package com.example.myfood.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myfood.DrinkFragment
import com.example.myfood.FoodFragment

class ViewPagerAdapter(fA: FragmentActivity) : FragmentStateAdapter(fA) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            FoodFragment()
        } else {
            DrinkFragment()
        }
    }
}