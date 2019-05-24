package com.example.cookbook

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.beardedhen.androidbootstrap.TypefaceProvider
import com.example.cookbook.database.CookBookDatabase
import com.example.cookbook.database.Ingredient
import kotlinx.android.synthetic.main.activity_tobuy.*
import java.util.*

class MyIngredients : MyActivity() {
    private var list = ArrayList<Ingredient>()
    lateinit var db : CookBookDatabase
    lateinit var myadapter : MyIngredientsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myingredients)
        TypefaceProvider.registerDefaultIconSets();

        recyclerview.layoutManager = LinearLayoutManager(this)

        db = CookBookDatabase.getInstance(this)
        refresh()
    }

    fun refresh(){
        list = db.ingredientDao().getOwnedIngredients() as ArrayList<Ingredient>
        myadapter = MyIngredientsAdapter(list)
        recyclerview.adapter = myadapter
    }

    fun addIngredient(view: View){
        //todo: jak poprawnie wstawic dialog z layoutem z dialog_ingredient? co z polem ID skladnika w bazie?
    }

    override fun clean() {
        for (elem in list) {
            elem.is_owned = false
            db.ingredientDao().update(elem)
        }
        refresh()
    }

    //dodanie menu kontekstowego
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menu.findItem(R.id.action_myingredients).isVisible = false
        return true
    }
}