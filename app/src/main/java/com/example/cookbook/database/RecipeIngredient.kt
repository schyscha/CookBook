package com.example.cookbook.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE

@Entity(tableName = "recipe_ingredient",
    primaryKeys = ["recipe_id", "ingredient_id"],
    foreignKeys = [
        ForeignKey(entity = Recipe::class,
            parentColumns = ["id"],
            childColumns = ["recipe_id"],
            onDelete=CASCADE),
        ForeignKey(entity = Ingredient::class,
            parentColumns = ["id"],
            childColumns = ["ingredient_id"],
            onDelete=CASCADE)
    ])
data class RecipeIngredient(
    var recipe_id: Long,
    var ingredient_id: Long,
    var quantity: Int,
    var unit: String
)
