package com.example.cookbook.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE

@Entity(tableName = "recipe_tag",
    primaryKeys = ["recipe_id", "tag_id"],
    foreignKeys = [
        ForeignKey(entity = Recipe::class,
            parentColumns = ["id"],
            childColumns = ["recipe_id"],
            onDelete=CASCADE),
        ForeignKey(entity = Tag::class,
            parentColumns = ["id"],
            childColumns = ["tag_id"],
            onDelete=CASCADE)
    ])
data class RecipeTag(
    var recipe_id: Long,
    var tag_id: Long
)