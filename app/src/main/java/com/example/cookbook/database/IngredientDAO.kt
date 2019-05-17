package com.example.cookbook.database

import androidx.room.*

@Dao
interface IngredientDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(ingredient: Ingredient): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg ingredient: Ingredient)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(ingredient: Ingredient)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateAll(vararg ingredient: Ingredient)

    @Delete
    fun delete(ingredient: Ingredient)

    @Query("Select * from ingredients where id = :id")
    fun getIngredient(id: Long): List<Ingredient>

    @Query("Select * from ingredients")
    fun getAll(): List<Ingredient>

    @Query("Select * from ingredients i Inner Join recipe_ingredient ri on i.id = ri.ingredient_id Where ri.recipe_id = :recipeID")
    fun getIngredientsForRecipe(recipeID: Long): List<Ingredient>

    @Query("""
        Select * from ingredients i Inner Join recipe_ingredient ri on i.id = ri.ingredient_id
        Where ri.recipe_id = :recipeID And i.is_owned = 0
    """)
    fun getIngredientsToBuyForRecipe(recipeID: Long): List<Ingredient>

    @Query("Select * from ingredients Where is_owned = 1")
    fun getOwnedIngredients(): List<Ingredient>

    @Query("Select * from ingredients Where name = :name")
    fun getByName(name: String): List<Ingredient>
}