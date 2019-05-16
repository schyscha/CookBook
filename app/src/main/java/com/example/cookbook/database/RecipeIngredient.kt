package com.example.cookbook.database

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "recipe_ingredient",
    primaryKeys = ["recipe_id", "ingredient_id"],
    foreignKeys = [
        ForeignKey(entity = Recipe::class,
            parentColumns = ["id"],
            childColumns = ["recipe_id"]),
        ForeignKey(entity = Ingredient::class,
            parentColumns = ["id"],
            childColumns = ["ingredient_id"])
    ])
data class RecipeIngredient(
    var recipe_id: Long,
    var ingredient_id: Long,
    var quantity: Int,
    var unit: String
)
