package com.example.cookbook

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.beardedhen.androidbootstrap.TypefaceProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.util.Log
import com.example.cookbook.database.*

class MainActivity : MyActivity() {

    private var backPressedTime: Long = 0
    private lateinit var backToast : Toast


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        TypefaceProvider.registerDefaultIconSets();



        recyclerview.layoutManager = LinearLayoutManager(this)

        var list = ArrayList<Dish>()
        //todo: sciagniecie bazy i umieszczenie jej obiektow jako Dish w list

        val myadapter = DishAdapter(list)
        recyclerview.adapter = myadapter

        val database = CookBookDatabase.getInstance(this)
        val recipeDAO = database.recipeDao()
        val ingredientDAO = database.ingredientDao()
        Log.e("CB", recipeDAO.getBestRecipesForOwnedIngredients().toString())
        Log.e("CB", recipeDAO.getRecipesBySearch("paszteciki").toString())
        Log.e("CB", ingredientDAO.getIngredientsToBuyForRecipe(3).toString())
        Log.e("CB", database.getAllCompleteRecipe().toString())
        /*
        val tagDao = database.tagDao()
        val ingredientDAO = database.ingredientDao()
        val recipeIngredientDAO = database.recipeIngredientDao()
        val recipeTagDAO = database.recipeTagDao()*/
    }

    //obsługa wyjscia z aplikajci po podwojnym kliknieciu WSTECZ w glownej aktywnosci
    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel()
            super.onBackPressed()
            if (getIntent().getBooleanExtra("EXIT", false)) {
                finish()
            }
            return
        } else {
            backToast = Toast.makeText(baseContext, "Naciśnij WSTECZ jeszcze raz żeby wyjść", Toast.LENGTH_SHORT)
            backToast.show()
        }

        backPressedTime = System.currentTimeMillis()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menu.findItem(R.id.action_main).isVisible = false
        return true
    }

    override fun clean() {

    }


    //todo: funkcje sortujące do przycisków
    fun sortAZ(view: View){

    }

    fun sortStars(view: View){

    }

    //todo: dodawanie do bazy potraw
    fun add(view: View){

    }
}

