package com.example.myfood.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfood.Item
import com.example.myfood.R
import com.example.myfood.activity.AddCart

class Food_RV_Adapter(private val items: MutableList<Item>) : RecyclerView.Adapter<Food_RV_Adapter.ItemViewHolder>() {

    private var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onDeleteClick(position: Int) // New method for delete action
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_food_rv, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.imageView.setImageResource(item.imageResId_food)
        holder.headTextView.text = item.head_food
        holder.subheadTextView.text = item.description_head
        holder.locationTextView.text = item.location_food
        holder.ratingTextView.text = item.rating_food

        holder.cardView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, AddCart::class.java)
            intent.putExtra("item", item)
            context.startActivity(intent)
        }

        // Set up the delete button click listener
        holder.deleteButton.setOnClickListener {
            listener?.onDeleteClick(position) // Notify the listener for delete action
        }
    }

    override fun getItemCount(): Int = items.size

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.card_view_RV)
        val imageView: ImageView = itemView.findViewById(R.id.imageView_RV)
        val headTextView: TextView = itemView.findViewById(R.id.head_TextView)
        val subheadTextView: TextView = itemView.findViewById(R.id.description_TextView)
        val locationTextView: TextView = itemView.findViewById(R.id.locationTextView)
        val ratingTextView: TextView = itemView.findViewById(R.id.ratingTextView)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton) // Assuming you have a delete button in your layout
    }
}