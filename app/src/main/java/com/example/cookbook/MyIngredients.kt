package com.example.cookbook

import android.app.Dialog
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.beardedhen.androidbootstrap.TypefaceProvider
import com.example.cookbook.database.CookBookDatabase
import com.example.cookbook.database.Ingredient
import kotlinx.android.synthetic.main.activity_tobuy.*
import kotlinx.android.synthetic.main.dialog_ingredient.*
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
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_ingredient)

        dialog.btn_cancel.setOnClickListener { dialog.hide() }
        dialog.btn_add.setOnClickListener {
            var input = dialog.editText.text.toString()
            if (input.isNotEmpty())
                newIngredient(input, dialog.checkBox.isChecked)
            dialog.hide()
        }

        dialog.show()
    }

    fun newIngredient(name : String, owned : Boolean){
        val ingredient = Ingredient(0, name, owned)
        db.ingredientDao().insert(ingredient)
        refresh()
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