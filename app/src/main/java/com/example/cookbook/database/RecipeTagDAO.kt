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

    @Query("Select * from tags t Inner Join recipe_tag rt on t.id = rt.tag_id Where rt.recipe_id = :recipeID")
    fun getTagsForRecipe(recipeID: Long): List<Tag>

    @Query("Select * from recipes r Inner Join recipe_tag rt on r.id = rt.recipe_id Where rt.tag_id = :tagID")
    fun getRecipesForTag(tagID: Long): List<Recipe>
}