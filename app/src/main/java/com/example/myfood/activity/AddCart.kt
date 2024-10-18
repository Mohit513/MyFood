package com.example.myfood.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myfood.AddedCartItem
import com.example.myfood.FavouriteFragment
import com.example.myfood.R
import com.example.myfood.Item
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AddCart : AppCompatActivity() {

    private lateinit var cart_Button: Button
    private lateinit var favorite_Button: FloatingActionButton
    private val cartItems = mutableListOf<Item>()
    private val favoriteItems = mutableListOf<Item>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_cart)
        loadFavorites()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val item = intent.getParcelableExtra<Item>("item")


        if (item != null) {
            val imageView: ImageView = findViewById(R.id.imageView_Detail)
            val headTextView: TextView = findViewById(R.id.head_TextView_Detail)
            val subheadTextView: TextView = findViewById(R.id.description_TextView_Detail)
            val locationTextView: TextView = findViewById(R.id.locationTextView_Detail)
            val ratingTextView: TextView = findViewById(R.id.ratingTextView_Detail)

            // Set the data to the views
            imageView.setImageResource(item.imageResId_food)
            headTextView.text = item.head_food
            subheadTextView.text = item.description_head
            locationTextView.text = item.location_food
            ratingTextView.text = item.rating_food
        }

        cart_Button = findViewById(R.id.cart_button)
        favorite_Button = findViewById(R.id.fav_floatingActionButton)

        cart_Button.setOnClickListener {
            item?.let {
                cartItems.add(it)
                Toast.makeText(this, "Item is added to cart", Toast.LENGTH_SHORT).show()

                saveCartItems(cartItems)

                val intent = Intent(this, AddedCartItem::class.java)
                startActivity(intent)
            }
        }



        favorite_Button.setOnClickListener {
            item?.let {
                if (favoriteItems.contains(it)) {
                    favoriteItems.remove(it)
                    favorite_Button.isSelected = false
                    Toast.makeText(this, "Item is removed from favorites", Toast.LENGTH_SHORT).show()
                        val intent = Intent(
                            this,
                            FavouriteFragment::class.java
                        )
                        intent.putParcelableArrayListExtra(
                            "favorite_items",
                            ArrayList(favoriteItems)
                        )
                        startActivity(intent)


                } else {
                    favoriteItems.add(it)
                    favorite_Button.isSelected = true
                    Toast.makeText(this, "Item is added to favorites", Toast.LENGTH_SHORT).show()
                }
                saveFavorites()
            }
        }


    }
    private fun loadFavorites() {
        val sharedPreferences = getSharedPreferences("MyFoodPrefs", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("favorite_items", null)
        val type = object : TypeToken<MutableList<Item>>() {}.type
        favoriteItems.clear()
        if (json != null) {
            favoriteItems.addAll(gson.fromJson(json, type))
        }
    }

    private fun saveFavorites() {
        val sharedPreferences = getSharedPreferences("MyFoodPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(favoriteItems)
        editor.putString("favorite_items", json)
        editor.apply()
    }

    private fun saveCartItems(cartItems: List<Item>) {
        val sharedPreferences = getSharedPreferences("MyFoodPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(cartItems)
        editor.putString("cart_items", json)
        editor.apply()
    }
}