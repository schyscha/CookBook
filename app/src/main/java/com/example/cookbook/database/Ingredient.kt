package com.example.cookbook.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients")
data class Ingredient(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var name: String,
    var is_owned: Boolean
)