package com.example.myfood

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myfood.adapter.CartAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AddedCartItem : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var addButton: Button
    private lateinit var deleteButton: Button
    private val cartItems = mutableListOf<Item>()
    private lateinit var adapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_added_cart_item)


        loadCartItems()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        listView = findViewById(R.id.listView_cartItem)
        addButton = findViewById(R.id.addButton)
        deleteButton = findViewById(R.id.deleteButton)


        adapter = CartAdapter(this, cartItems)
        listView.adapter = adapter


        addButton.setOnClickListener {

           val intent = Intent(this, HomeFragment::class.java)
            startActivity(intent)
        }

        deleteButton.setOnClickListener {

            val selectedItem = getSelectedItem()
            if (selectedItem != null) {
                cartItems.remove(selectedItem)
                adapter.notifyDataSetChanged()
                saveCartItems() // Save updated cart items
                Toast.makeText(this, "Item removed", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No item selected", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadCartItems() {
        val sharedPreferences = getSharedPreferences("MyFoodPrefs", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("cart_items", null)
        val type = object : TypeToken<MutableList<Item>>() {}.type
        if (json != null) {
            val items: List<Item> = gson.fromJson(json, type)
            cartItems.clear()
            cartItems.addAll(items)
        }
    }

    private fun saveCartItems() {
        val sharedPreferences = getSharedPreferences("MyFoodPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(cartItems)
        editor.putString("cart_items", json)
        editor.apply()
    }

    private fun getSelectedItem(): Item? {
        Handler(Looper.getMainLooper()).postDelayed({
            Toast.makeText(this, "Add more or delete Item", Toast.LENGTH_SHORT).show()
        }, 5)
        return null
    }
}