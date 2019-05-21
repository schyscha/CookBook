package com.example.cookbook

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import com.beardedhen.androidbootstrap.TypefaceProvider
import com.example.cookbook.database.CompleteRecipe
import com.example.cookbook.database.CookBookDatabase
import kotlinx.android.synthetic.main.activity_dish.*


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

        ratingbar.rating = dish.recipe.rating

        nazwa.text = dish.recipe.name

        val tags= StringBuilder()
        tags.append(dish.tags.first().name)
        for(i in 1..dish.tags.size-1){
            tags.append(", ")
            tags.append(dish.tags.get(i).name)
        }
        tagi.text = tags.toString()

        val ingredients= StringBuilder()
        ingredients.append(dish.ingredientsInfo.first().name)
        ingredients.append(": ")
        ingredients.append(dish.ingredientsInfo.first().quantity)
        ingredients.append(" ")
        ingredients.append(dish.ingredientsInfo.first().unit)
        for(i in 1..dish.ingredientsInfo.size-1){
            ingredients.append("\n")
            ingredients.append(dish.ingredientsInfo.get(i).name)
            ingredients.append(": ")
            ingredients.append(dish.ingredientsInfo.get(i).quantity)
            ingredients.append(" ")
            ingredients.append(dish.ingredientsInfo.get(i).unit)
        }
        skladniki.text = ingredients.toString()

        sposob.text = dish.recipe.instruction

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