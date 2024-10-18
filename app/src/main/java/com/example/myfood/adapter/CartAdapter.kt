package com.example.myfood.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.myfood.Item
import com.example.myfood.R
import com.example.myfood.activity.AddCart

class CartAdapter(private val context: Context, private val cartItems: MutableList<Item>) :
    ArrayAdapter<Item>(context, 0, cartItems) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.cartitem, parent, false)

        val cartItem = cartItems[position]
        val cardView: CardView = view.findViewById(R.id.cardView_CartList)
        val imageView: ImageView = view.findViewById(R.id.imageView_CartList)
        val headTextView: TextView = view.findViewById(R.id.headTextView_cartList)
        val subheadTextView: TextView = view.findViewById(R.id.subheadTextView_cartList)
        val locationTextView: TextView = view.findViewById(R.id.locationTextView_cartList)
        val ratingTextView: TextView = view.findViewById(R.id.ratingTextView_cartList)


        imageView.setImageResource(cartItem.imageResId_food)
        headTextView.text = cartItem.head_food
        subheadTextView.text = cartItem.description_head
        locationTextView.text = cartItem.location_food
        ratingTextView.text = cartItem.rating_food
        cardView.setOnClickListener {
            val intent = Intent(context, AddCart::class.java)
            intent.putExtra("item", cartItem)
            context.startActivity(intent)
        }

        return cardView
    }
}