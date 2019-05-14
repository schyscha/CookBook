package com.example.cookbook.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var name: String,
    var image_urls: ArrayList<String>,
    var instruction: String,
    var rating: Float
)