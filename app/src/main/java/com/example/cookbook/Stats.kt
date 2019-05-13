package com.example.cookbook

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.beardedhen.androidbootstrap.TypefaceProvider
import kotlinx.android.synthetic.main.activity_stats.*

class Stats : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)
        TypefaceProvider.registerDefaultIconSets();

    }

    //dodanie menu kontekstowego
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        var item = menu.findItem(R.id.action_clean)
        item.setVisible(false)
        var self = menu.findItem(R.id.action_stats)
        self.setVisible(false)
        return true
    }

    //menu kontekstowe
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_main -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_tobuy -> {
                val intent = Intent(this, ToBuy::class.java)
                startActivity(intent)
                true
            }
            R.id.action_stats -> {
                true
            }
            R.id.action_search -> {
                val intent = Intent(this, FindDish::class.java)
                startActivity(intent)
                true
            }
            R.id.action_clean -> {
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
        builder.setMessage(
            Html.fromHtml("<b>Wersja 0.3<br><br>Autorzy:</b><br><i> " +
                    "Olga Błaszczyk<br> Bartosz Drzaga<br> Filip Gawin<br> Szymon Rozmarynowski</i>"))

        builder.setPositiveButton("Zamknij"){dialog, which ->}
        dialog = builder.create()
        dialog.show()
    }

}