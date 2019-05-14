package com.example.cookbook.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Ingredient::class, Recipe::class, RecipeIngredient::class, RecipeTag::class, Tag::class], version = 1)
@TypeConverters(TypeConverter::class)
abstract class CookBookDatabase: RoomDatabase() {
    abstract fun ingredientDao(): IngredientDAO
    abstract fun recipeDao(): RecipeDAO
    abstract fun recipeIngredientDao(): RecipeIngredientDAO
    abstract fun recipeTagDao(): RecipeTagDAO
    abstract fun tagDao(): TagDAO

    companion object {
        @Volatile
        private var INSTANCE: CookBookDatabase? = null

        fun getInstance(context: Context): CookBookDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                CookBookDatabase::class.java,
                "CookBookDatabase.db")
                .allowMainThreadQueries()
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        //prepopulate data here
                    }
                })
                .build()
    }
}