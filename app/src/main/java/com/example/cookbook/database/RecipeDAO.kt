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
}