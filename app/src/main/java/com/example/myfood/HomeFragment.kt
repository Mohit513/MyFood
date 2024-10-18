package com.example.myfood

import android.content.Intent
import com.example.myfood.adapter.ViewPagerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import com.example.myfood.activity.AddCart
import com.google.android.material.search.SearchBar
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager_home: ViewPager2
    private lateinit var searchBar: SearchBar
    private lateinit var cartButton: ImageView

    private val ARG_PARAM1 = "param1"
    private val ARG_PARAM2 = "param2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        tabLayout = view.findViewById(R.id.tabLayout_Home)
        viewPager_home = view.findViewById(R.id.viewPager_Home2)
        searchBar = view.findViewById(R.id.searchView)
        cartButton = view.findViewById(R.id.imageView_cart)

        // Set up the adapter for the ViewPager
        val adapter = ViewPagerAdapter(requireActivity())
        viewPager_home.adapter = adapter

        searchBar.setHint("Search")

        // Set up the TabLayout
        tabLayout.addTab(tabLayout.newTab().setText("food"))
        tabLayout.addTab(tabLayout.newTab().setText("drink"))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                 if (tab != null){
                     viewPager_home.currentItem = tab.position
                 }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
        cartButton.setOnClickListener {
            val intent = Intent(requireContext(), AddedCartItem::class.java)
            startActivity(intent)
        }

        viewPager_home.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })

        return view
    }

}


