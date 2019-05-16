package com.example.cookbook.database

import android.content.Context
import androidx.room.*

@Database(entities = [Ingredient::class, Recipe::class, RecipeIngredient::class, RecipeTag::class, Tag::class], version = 1)
@TypeConverters(TypeConverter::class)
abstract class CookBookDatabase: RoomDatabase() {
    abstract fun ingredientDao(): IngredientDAO
    abstract fun recipeDao(): RecipeDAO
    abstract fun recipeIngredientDao(): RecipeIngredientDAO
    abstract fun recipeTagDao(): RecipeTagDAO
    abstract fun tagDao(): TagDAO

    //wzorzec Singleton
    companion object {
        @Volatile
        private var INSTANCE: CookBookDatabase? = null

        fun getInstance(context: Context): CookBookDatabase =
            INSTANCE ?: synchronized(this) { //blokada z podwójnym zatwierdzeniem
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context): CookBookDatabase {
            val db = Room.databaseBuilder(
                context.applicationContext,
                CookBookDatabase::class.java,
                "CookBookDatabase.db"
            )
                .allowMainThreadQueries()
                .build()
            loadInitialData(context, db)
            return db
        }

        private fun loadInitialData(context: Context, db: CookBookDatabase) {
            if(db.tagDao().getAll().isEmpty()) { //zakładam że jeśli w bazie nie ma żadnych tagów to baza jest pusta
                db.runInTransaction(TestDataLoader(context, db)) //i dodaję do niej początkowe dane
            }
        }
    }



    @Transaction
    fun getCompleteRecipe(recipeID: Long): List<CompleteRecipe> {
        val result = ArrayList<CompleteRecipe>()
        val recipeList = recipeDao().getRecipe(recipeID)
        for(r in recipeList) {
            val ingredientList = ArrayList(ingredientDao().getIngredientsForRecipe(recipeID))
            val cil = ArrayList<CompleteRecipeIngredientRelation>()

            for(i in ingredientList) {
                val ri = recipeIngredientDao().getRecipeIngredientRelationForRecipeAndIngredient(recipeID, i.id)
                if(ri.isEmpty()) continue
                cil.add(CompleteRecipeIngredientRelation(i.id, i.name, ri[0].quantity, ri[0].unit, i.is_owned))
            }

            val t = ArrayList(tagDao().getTagsForRecipe(recipeID))
            result.add(CompleteRecipe(r, cil, t))
        }
        return result
    }

    @Transaction
    fun getAllCompleteRecipe(): List<CompleteRecipe> {
        val recipes = recipeDao().getAll()
        val result = ArrayList<CompleteRecipe>()
        for(recipe in recipes) {
            result.addAll(getCompleteRecipe(recipe.id))
        }
        return result
    }
}