package com.example.cookbook.database

import androidx.room.*

@Dao
interface RecipeDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(recipe: Recipe): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg recipe: Recipe)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(recipe: Recipe)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateAll(vararg recipe: Recipe)

    @Delete
    fun delete(recipe: Recipe)

    @Query("Select * from recipes Where id = :id")
    fun getRecipe(id: Long): List<Recipe>

    @Query("Select * from recipes")
    fun getAll(): List<Recipe>

    @Query("Select * from recipes r Inner Join recipe_ingredient ri on r.id = ri.recipe_id Where ri.ingredient_id = :ingredientID")
    fun getRecipesForIngredient(ingredientID: Long): List<Recipe>

    @Query("""
        Select * from recipes r Inner Join recipe_ingredient ri on r.id = ri.recipe_id
        Where ri.ingredient_id IN (:ingredientsIDs) Group By r.name Having Count(ri.ingredient_id) > 0
        Order By Count(ri.ingredient_id) DESC
    """)
    fun getBestRecipesForSelectedIngredients(ingredientsIDs: List<Long>): List<Recipe>

    @Query("""
        Select * from recipes r Inner Join recipe_ingredient ri on r.id = ri.recipe_id
        Where ri.ingredient_id IN (Select id From ingredients Where is_owned = 1) Group By r.name Having Count(ri.ingredient_id) > 0
        Order By Count(ri.ingredient_id) DESC
    """)
    fun getBestRecipesForOwnedIngredients(): List<Recipe>

    @Query("Select * from recipes r Inner Join recipe_tag rt on r.id = rt.recipe_id Where rt.tag_id = :tagID")
    fun getRecipesForTag(tagID: Long): List<Recipe>

    @Query("Select * from recipes Where name Like '%' || :name || '%'") //operator || to konkatenacja stringów w SQLite
    fun getRecipesBySearch(name: String): List<Recipe>
}