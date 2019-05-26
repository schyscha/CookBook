package com.example.cookbook

import android.app.Dialog
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.beardedhen.androidbootstrap.TypefaceProvider
import kotlinx.android.synthetic.main.activity_main.*
import com.example.cookbook.database.*
import kotlinx.android.synthetic.main.dialog_dish.*
import kotlinx.android.synthetic.main.dialog_dish.btn_add
import kotlinx.android.synthetic.main.dialog_dish.btn_cancel
import java.util.Collections.reverse
import java.util.Collections.sort
import kotlin.collections.ArrayList


class MainActivity : MyActivity() {
    private var backPressedTime: Long = 0
    private lateinit var backToast : Toast
    private var alphabeticalOrder = false
    private var ratingOrder = false
    lateinit var list: ArrayList<CompleteRecipe>
    lateinit var tempList: List<Recipe>
    private lateinit var myadapter : DishAdapter
    private var searching : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        TypefaceProvider.registerDefaultIconSets()

        val search : String? = intent.getStringExtra("search")
        list =  CookBookDatabase.getInstance(this).getAllCompleteRecipe() as ArrayList<CompleteRecipe>

        if (search != null) {
            searching = true
            list.clear()
            tempList = CookBookDatabase.getInstance(this).recipeDao().getRecipesBySearch(search)
            for (recipe in tempList)
                list.addAll(CookBookDatabase.getInstance(this).getCompleteRecipe(recipe.id))
        }
        else
            searching = false

        myadapter = DishAdapter(list)

        recyclerview.layoutManager = LinearLayoutManager(this)

        myadapter = DishAdapter(list)
        recyclerview.adapter = myadapter
    }

    //obsługa wyjscia z aplikacji po podwojnym kliknieciu WSTECZ w glownej aktywnosci
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
        if (!searching)
            menu.findItem(R.id.action_main).isVisible = false
        menu.findItem(R.id.action_clean).isVisible = false
        return true
    }

    fun sortAZ(view: View){
        if (!alphabeticalOrder) {
            sort(list, { a, b -> a.recipe.name.compareTo(b.recipe.name) })
            alphabeticalOrder = true
            ratingOrder = false
        }
        else {
            reverse(list)
            alphabeticalOrder = false
            ratingOrder = false
        }

        myadapter.setDishes(list)
        myadapter.notifyDataSetChanged()
    }

    fun sortStars(view: View){
        if (!ratingOrder) {
            sort(list, { a, b -> a.recipe.rating.compareTo(b.recipe.rating) })
            ratingOrder = true
            alphabeticalOrder = false
        }
        else {
            reverse(list)
            ratingOrder = false
            alphabeticalOrder = false
        }

        myadapter.setDishes(list)
        myadapter.notifyDataSetChanged()
    }

    fun add(view: View){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_dish)
        dialog.show()

        dialog.btn_cancel.setOnClickListener { dialog.hide() }
        dialog.btn_add.setOnClickListener {
            val name = dialog.dishNameTextEdit.text.toString()
            val instr = dialog.instructionsTextEdit.text.toString()
            val rating = dialog.ratingbar.rating

            var linksArray = ArrayList<String>()
            val links = dialog.ImgLinksTextEdit.text.toString()
            if (links.isNotEmpty()) {
                val splited = links.split(",", ", ")
                if(splited.size > 1) {
                    linksArray = splited as ArrayList<String>
                } else {
                    linksArray.add(links)
                }
            }

            var tagsArray = ArrayList<String>()
            val tags = dialog.tagsTextEdit.text.toString()
            if (tags.isNotEmpty()) {
                val splited = tags.split(",", ", ")
                if(splited.size > 1) {
                    tagsArray = splited as ArrayList<String>
                } else {
                    tagsArray.add(tags)
                }
            }

            var ingrArray = ArrayList<String>()
            val ingredients = dialog.ingrTextEdit.text.toString()
            if (ingredients.isNotEmpty()) {
                val splited = ingredients.split(",", ", ")
                if(splited.size > 1) {
                    ingrArray = splited as ArrayList<String>
                } else {
                    ingrArray.add(ingredients)
                }
            }

            if (name.isNotEmpty() && instr.isNotEmpty())
                newDish(name, linksArray, instr, ingrArray, tagsArray, rating)
            dialog.hide()
        }
    }

    fun newDish(name : String, links: ArrayList<String>, instruction: String, ingriedients: ArrayList<String>, tags: ArrayList<String>, rating: Float){
        var relations = ArrayList<CompleteRecipeIngredientRelation>()

        for (ingr in ingriedients) {
            val ingrDataArray = ingr.split(" ")
            Toast.makeText(getApplicationContext(), "size to " + ingrDataArray.size, Toast.LENGTH_SHORT).show()
            if(ingrDataArray.size == 3) {
                relations.add(CompleteRecipeIngredientRelation(0, ingrDataArray[0], ingrDataArray[1].toInt(), ingrDataArray[2], false))
            }
        }

        val db = CookBookDatabase.getInstance(this)
        db.insert(name, links, instruction, rating, relations, tags)

        refresh()
    }

    fun refresh(){
        val db = CookBookDatabase.getInstance(this)
        list = db.getAllCompleteRecipe() as ArrayList<CompleteRecipe>
        myadapter.setDishes(list)
        myadapter.notifyDataSetChanged()
    }
}

