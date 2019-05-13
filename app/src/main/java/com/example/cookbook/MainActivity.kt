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

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        TypefaceProvider.registerDefaultIconSets();

        recyclerview.layoutManager = LinearLayoutManager(this)

        var list = ArrayList<Dish>()
        //todo: sciagniecie bazy i umieszczenie jej obiektow jako Dish w list

        val myadapter = DishAdapter(list)
        recyclerview.adapter = myadapter

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
                val intent = Intent(this, FindDish::class.java)
                startActivity(intent)
                true
            }
            R.id.action_clean -> {
                //todo: fill
                true
            }
            R.id.action_about -> {
                showDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    //okno dialogowe z menu kontekstowego
    private fun showDialog(){
        lateinit var dialog : AlertDialog

        val builder = AlertDialog.Builder(this)

        builder.setTitle("O aplikacji")
        builder.setMessage(Html.fromHtml("<b>Wersja 0.3<br><br>Autorzy:</b><br><i> " +
                "Olga Błaszczyk<br> Bartosz Drzaga<br> Filip Gawin<br> Szymon Rozmarynowski</i>"))

        builder.setPositiveButton("Zamknij"){dialog, which ->}
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

