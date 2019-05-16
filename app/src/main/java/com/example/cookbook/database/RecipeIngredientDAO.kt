package com.example.cookbook.database

import androidx.room.*

@Dao
interface RecipeIngredientDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(recipeIngredient: RecipeIngredient): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg recipeIngredient: RecipeIngredient)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(recipeIngredient: RecipeIngredient)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateAll(vararg recipeIngredient: RecipeIngredient)

    @Delete
    fun delete(recipeIngredient: RecipeIngredient)

    @Query("Select * from recipe_ingredient Where recipe_id = :recipeID")
    fun getRecipeIngredientRelationsForRecipe(recipeID: Long): List<RecipeIngredient>

    @Query("Select * from recipe_ingredient Where ingredient_id = :ingredientID")
    fun getRecipeIngredientRelationsForIngredient(ingredientID: Long): List<RecipeIngredient>

    @Query("Select * from recipe_ingredient Where recipe_id = :recipeID And ingredient_id = :ingredientID")
    fun getRecipeIngredientRelationForRecipeAndIngredient(recipeID: Long, ingredientID: Long): List<RecipeIngredient>
}