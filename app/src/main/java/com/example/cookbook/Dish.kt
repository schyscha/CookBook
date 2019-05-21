package com.example.cookbook

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import com.beardedhen.androidbootstrap.TypefaceProvider
import com.example.cookbook.database.CompleteRecipe
import com.example.cookbook.database.CookBookDatabase
import java.lang.StringBuilder

class Dish : MyActivity() {
    lateinit var dishL : List<CompleteRecipe>
    lateinit var dish : CompleteRecipe
    val db = CookBookDatabase.getInstance(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish)
        TypefaceProvider.registerDefaultIconSets();

        val id : Long = intent.getLongExtra("id", 0)
        dishL = db.getCompleteRecipe(id)
        dish = dishL.first()

        //todo: obrazki

        var _ratingbar : RatingBar = findViewById(R.id.ratingbar)
        _ratingbar.rating = dish.recipe.rating

        var _nazwa : TextView = findViewById(R.id.nazwa)
        _nazwa.text = dish.recipe.name

        var _tagi : TextView = findViewById(R.id.tagi)
        val tags= StringBuilder()
        tags.append(dish.tags.first().name)
        for(i in 1..dish.tags.size){
            tags.append(", ")
            tags.append(dish.tags.get(i).name)
        }
        _tagi.text = tags.toString()

        var _skladniki : TextView = findViewById(R.id.skladniki)
        val ingredients= StringBuilder()
        ingredients.append(dish.ingredientsInfo.first().name)
        ingredients.append(": ")
        ingredients.append(dish.ingredientsInfo.first().quantity)
        ingredients.append(" ")
        ingredients.append(dish.ingredientsInfo.first().unit)
        for(i in 1..dish.ingredientsInfo.size){
            tags.append(", ")
            tags.append(dish.ingredientsInfo.get(i).name)
            ingredients.append(": ")
            ingredients.append(dish.ingredientsInfo.get(i).quantity)
            ingredients.append(" ")
            ingredients.append(dish.ingredientsInfo.get(i).unit)
        }
        _skladniki.text = ingredients.toString()

        var _sposob : TextView = findViewById(R.id.sposob)
        _sposob.text = dish.recipe.instruction

    }

    //funkcja usuwająca potrawę z bazy
    fun delete(view: View){
        //todo: funkcja usuwajaca potrawe z bazy(listy)
        val intent = Intent(this, MainActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }

    //dodanie menu kontekstowego
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menu.findItem(R.id.action_clean).isVisible = false
        return true
    }

}