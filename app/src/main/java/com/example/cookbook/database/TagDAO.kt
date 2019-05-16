package com.example.cookbook.database

import androidx.room.*

@Dao
interface TagDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(tag: Tag): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg tag: Tag)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(tag: Tag)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateAll(vararg tag: Tag)

    @Delete
    fun delete(tag: Tag)

    @Query("Select * from tags Where id = :id")
    fun getTag(id: Long): List<Tag>

    @Query("Select * from tags")
    fun getAll(): List<Tag>

    @Query("Select * from tags t Inner Join recipe_tag rt on t.id = rt.tag_id Where rt.recipe_id = :recipeID")
    fun getTagsForRecipe(recipeID: Long): List<Tag>
}