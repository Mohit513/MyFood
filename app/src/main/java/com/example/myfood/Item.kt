package com.example.myfood
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
@Parcelize
data class Item( val head_food: String,
                 val description_head: String,
                 val location_food: String,
                 val rating_food: String,
                 val imageResId_food: Int ): Parcelable
