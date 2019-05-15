package com.example.cookbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.beardedhen.androidbootstrap.TypefaceProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList
import android.widget.Toast
import android.R.string.cancel
import android.content.DialogInterface
import android.text.InputType
import android.util.Log
import android.widget.EditText
import com.example.cookbook.database.*


class MainActivity : AppCompatActivity() {

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
        Log.e("CB", recipeDAO.getAll().toString())
        /*
        val tagDao = database.tagDao()
        val ingredientDAO = database.ingredientDao()
        val recipeIngredientDAO = database.recipeIngredientDao()
        val recipeTagDAO = database.recipeTagDao()

        val r = recipeDAO.getRecipe(1)[0]
        Log.e("CB", r.toString())
        Log.e("CB", recipeTagDAO.getTagsForRecipe(r.id).toString())
        Log.e("CB", recipeIngredientDAO.getIngredientsForRecipe(r.id).toString())*/
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

    //dodanie menu kontekstowego
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        var self = menu.findItem(R.id.action_main)
        self.setVisible(false)
        return true
    }

    //menu kontekstowe
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_main -> {
                true
            }
            R.id.action_tobuy -> {
                val intent = Intent(this, ToBuy::class.java)
                startActivity(intent)
                true
            }
            R.id.action_stats -> {
                val intent = Intent(this, Stats::class.java)
                startActivity(intent)
                true
            }
            R.id.action_search -> {
                showSearchDialog()
                true
            }
            R.id.action_finddish -> {
                val intent = Intent(this, FindDish::class.java)
                startActivity(intent)
                true
            }
            R.id.action_clean -> {
                showCleanDialog()
                true
            }
            R.id.action_about -> {
                showAboutDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    //okno dialogowe o aplikacji z menu kontekstowego
    private fun showAboutDialog(){
        lateinit var dialog : AlertDialog

        val builder = AlertDialog.Builder(this)

        builder.setTitle("O aplikacji")
        builder.setMessage(Html.fromHtml("<b>Wersja 0.4<br><br>Autorzy:</b><br><i> " +
                "Olga Błaszczyk<br> Bartosz Drzaga<br> Filip Gawin<br> Szymon Rozmarynowski</i>"))

        builder.setPositiveButton("Zamknij"){dialog, which ->}
        dialog = builder.create()
        dialog.show()
    }

    //okno dialogowe wyszukiwania potraw z menu kontekstowego
    private fun showSearchDialog(){
        lateinit var search: String
        lateinit var dialog : AlertDialog
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Wyszukaj potrawę")

        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        builder.setPositiveButton(
            "Wyszukaj"
        ) { dialog, which -> search = input.text.toString()}//todo: wyszukiwanie, pokazywanie rezultatow
        builder.setNegativeButton(
            "Anuluj"
        ) { dialog, which -> dialog.cancel() }

        dialog = builder.create()
        dialog.show()
    }

    //okno dialogowe usuwania zawartosci listy z menu kontekstowego
    private fun showCleanDialog(){
        lateinit var dialog:AlertDialog

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Usunąć wszystkie pozycje?")

        builder.setMessage("Ta operacja nie może zostać cofnięta")


        builder.setPositiveButton(
            "Usuń"
        ) { dialog, which -> }//todo: usuwanie wszystkiego z listy potraw
        builder.setNegativeButton(
            "Anuluj"
        ) { dialog, which -> dialog.cancel() }

        dialog = builder.create()
        dialog.show()
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

