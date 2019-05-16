package com.example.cookbook.database

import androidx.room.*

@Dao
interface RecipeTagDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(recipeTag: RecipeTag): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg recipeTag: RecipeTag)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(recipeTag: RecipeTag)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateAll(vararg recipeTag: RecipeTag)

    @Delete
    fun delete(recipeTag: RecipeTag)
}