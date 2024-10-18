package com.example.myfood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfood.adapter.Food_RV_Adapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FavouriteFragment : Fragment() {

    private val favoriteItems = mutableListOf<Item>()
    private lateinit var progressDialog: RelativeLayout
    private lateinit var recyclerView: RecyclerView

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadFavorites()
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_favourite, container, false)

        progressDialog = view.findViewById(R.id.favouriteRestaurantProgressDialog)
        recyclerView = view.findViewById(R.id.recyclerViewFavourite)


        setupRecyclerView(view)

        return view
    }

    private fun setupRecyclerView(view: View) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = Food_RV_Adapter(favoriteItems)
        recyclerView.adapter = adapter

        showProgressDialog()


        recyclerView.postDelayed({
            hideProgressDialog()
            adapter.notifyDataSetChanged()
        }, 1000)

        adapter.setOnItemClickListener(object : Food_RV_Adapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                saveFavorites()
            }

            override fun onDeleteClick(position: Int) {

                favoriteItems.removeAt(position)
                adapter.notifyItemRemoved(position)
                adapter.notifyItemRangeChanged(position, favoriteItems.size)
                saveFavorites()
            }
        })
    }

    private fun showProgressDialog() {
        progressDialog.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    private fun hideProgressDialog() {
        progressDialog.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavouriteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun loadFavorites() {
        val sharedPreferences =
            requireActivity().getSharedPreferences("MyFoodPrefs", AppCompatActivity.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("favorite_items", null)
        val type = object : TypeToken<MutableList<Item>>() {}.type
        if (json != null) {
            favoriteItems.addAll(gson.fromJson(json, type))
        }
    }

    private fun saveFavorites() {
        val sharedPreferences =
            requireActivity().getSharedPreferences("MyFoodPrefs", AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(favoriteItems)
        editor.putString("favorite_items", json)
        editor.apply()
    }
}