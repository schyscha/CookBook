package com.example.cookbook

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.beardedhen.androidbootstrap.TypefaceProvider
import com.example.cookbook.database.CompleteRecipe
import com.example.cookbook.database.CookBookDatabase
import kotlinx.android.synthetic.main.activity_dish.*


class Dish : MyActivity() {
    lateinit var dishL : List<CompleteRecipe>
    lateinit var dish : CompleteRecipe
    lateinit var db : CookBookDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish)
        TypefaceProvider.registerDefaultIconSets()

        db = CookBookDatabase.getInstance(this)
        val id : Long = intent.getLongExtra("id", 0)
        dishL = db.getCompleteRecipe(id)
        dish = dishL.first()

        val myadapter = ImageAdapter(dish.recipe.image_urls)
        images.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        images.adapter = myadapter

        ratingbar.rating = dish.recipe.rating
        ratingbar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            dish.recipe.rating = ratingbar.rating
            db.recipeDao().update(dish.recipe)}

        nazwa.text = dish.recipe.name

        val tags= StringBuilder()
        if (tags.isNotEmpty()) {
            tags.append(dish.tags.first().name)
            for (i in 1..dish.tags.size - 1) {
                tags.append(", ")
                tags.append(dish.tags.get(i).name)
            }
        }
        tagi.text = tags.toString()

        val ingredients= StringBuilder()
        if (dish.ingredientsInfo.isNotEmpty()) {
            ingredients.append(dish.ingredientsInfo.first().name)
            ingredients.append(": ")
            ingredients.append(dish.ingredientsInfo.first().quantity)
            ingredients.append(" ")
            ingredients.append(dish.ingredientsInfo.first().unit)
        }
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
        db.recipeDao().delete(dish.recipe)
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