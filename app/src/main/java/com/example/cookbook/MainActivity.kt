package com.example.cookbook

import android.app.Dialog
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.beardedhen.androidbootstrap.TypefaceProvider
import kotlinx.android.synthetic.main.activity_main.*
import android.util.Log
import com.example.cookbook.database.*
import kotlinx.android.synthetic.main.dialog_dish.*
import kotlinx.android.synthetic.main.dialog_dish.btn_add
import kotlinx.android.synthetic.main.dialog_dish.btn_cancel
import kotlinx.android.synthetic.main.dialog_ingredient.*
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
        TypefaceProvider.registerDefaultIconSets();

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
            var links = dialog.ImgLinksTextEdit.text.toString().split(",") as ArrayList<String>
            val instr = dialog.instructionsTextEdit.text.toString()
            val rating = dialog.ratingbar.rating

            if(links.isEmpty())
                links = ArrayList<String>()

            if (name.isNotEmpty() && instr.isNotEmpty())
                newDish(name, links, instr, rating)
            dialog.hide()
        }
    }

    fun newDish(name : String, links: ArrayList<String>, instruction: String, rating: Float){
        val recipe = Recipe(0, name, links, instruction, rating)
        val db = CookBookDatabase.getInstance(this)
        db.recipeDao().insert(recipe)
        refresh()
    }

    fun refresh(){
        val db = CookBookDatabase.getInstance(this)
        list = db.getAllCompleteRecipe() as ArrayList<CompleteRecipe>
        myadapter.setDishes(list)
        myadapter.notifyDataSetChanged()
    }
}

