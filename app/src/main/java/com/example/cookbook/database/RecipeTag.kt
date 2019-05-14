package com.example.cookbook.database

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "recipe_tag",
    primaryKeys = ["recipe_id", "tag_id"],
    foreignKeys = [
        ForeignKey(entity = Recipe::class,
            parentColumns = ["id"],
            childColumns = ["recipe_id"]),
        ForeignKey(entity = Tag::class,
            parentColumns = ["id"],
            childColumns = ["tag_id"])
    ])
data class RecipeTag(
    var recipe_id: Long,
    var tag_id: Long
)