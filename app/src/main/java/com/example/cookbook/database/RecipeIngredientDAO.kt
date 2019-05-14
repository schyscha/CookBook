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

    @Query("Select * from ingredients i Inner Join recipe_ingredient ri on i.id = ri.ingredient_id Where ri.recipe_id = :recipeID")
    fun getIngredientsForRecipe(recipeID: Long): List<Ingredient>

    @Query("Select * from recipe_ingredient Where recipe_id = :recipeID")
    fun getRecipeIngredientForRecipe(recipeID: Long): List<RecipeIngredient>

    @Query("Select * from recipes r Inner Join recipe_ingredient ri on r.id = ri.recipe_id Where ri.ingredient_id = :ingredientID")
    fun getRecipesForIngredient(ingredientID: Long): List<Recipe>

    @Query("Select * from recipe_ingredient Where ingredient_id = :ingredientID")
    fun getRecipeIngredientForIngredient(ingredientID: Long): List<RecipeIngredient>
}