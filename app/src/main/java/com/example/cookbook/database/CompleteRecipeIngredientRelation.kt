package com.example.cookbook.database

data class CompleteRecipeIngredientRelation(
    var id: Long,
    var name: String,
    var quantity: Int,
    var unit: String,
    var is_owned: Boolean
)