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
}