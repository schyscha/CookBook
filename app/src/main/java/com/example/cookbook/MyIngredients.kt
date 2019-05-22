package com.example.cookbook

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.beardedhen.androidbootstrap.TypefaceProvider
import com.example.cookbook.database.CookBookDatabase
import com.example.cookbook.database.Ingredient
import com.example.cookbook.database.IngredientDAO_Impl
import com.example.cookbook.database.RecipeDAO
import kotlinx.android.synthetic.main.activity_tobuy.*
import java.util.*

class MyIngredients : MyActivity() {
    private var list = ArrayList<Ingredient>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myingredients)
        TypefaceProvider.registerDefaultIconSets();

        recyclerview.layoutManager = LinearLayoutManager(this)


        val db = CookBookDatabase.getInstance(this)
        list = db.ingredientDao().getAll() as ArrayList<Ingredient>

        var actual = ArrayList<Ingredient>()

        for (elem in list) {
            if (elem.is_owned) {
                actual.add(elem)
            }
        }


        val myadapter = MyIngredientsAdapter(actual)
        recyclerview.adapter = myadapter
    }

    //todo: dodawanie do bazy listy posiadanych składników
    fun addIngredient(view: View){

    }

    override fun clean() {

    }

    //dodanie menu kontekstowego
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menu.findItem(R.id.action_myingredients).isVisible = false
        return true
    }
}