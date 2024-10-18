package com.example.myfood.pojo

data class RecipeApi(
    val limit: Int,
    val recipes: List<Recipe>,
    val skip: Int,
    val total: Int
)