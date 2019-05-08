package com.example.cookbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beardedhen.androidbootstrap.TypefaceProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        TypefaceProvider.registerDefaultIconSets();


        //should work but not sure...
        recyclerview.layoutManager = GridLayoutManager(this,2) as RecyclerView.LayoutManager?

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_tobuy -> {
                //todo: fill
                true
            }
            R.id.action_stats -> {
                //todo: fill
                true
            }
            R.id.action_search -> {
                //todo: fill
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

    private fun showDialog(){
        lateinit var dialog : AlertDialog

        val builder = AlertDialog.Builder(this)

        builder.setTitle("O aplikacji")
        builder.setMessage(Html.fromHtml("<b>Wersja 1.0<br><br>Autorzy:</b><br><i> " +
                "Olga Błaszczyk<br> Bartosz Drzaga<br> Filip Gawin<br> Szymon Rozmarynowski</i>"))

        builder.setPositiveButton("Zamknij"){dialog, which ->}
        dialog = builder.create()
        dialog.show()
    }
}

