package com.example.cookbook.database

data class CompleteRecipe(
    var recipe: Recipe,
    var ingredientsInfo: ArrayList<CompleteRecipeIngredientRelation>,
    var tags: ArrayList<Tag>
)