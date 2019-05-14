package com.example.cookbook

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.beardedhen.androidbootstrap.TypefaceProvider
import kotlinx.android.synthetic.main.activity_tobuy.*
import java.util.ArrayList

class FindDish : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finddish)
        TypefaceProvider.registerDefaultIconSets()

        recyclerview.layoutManager = LinearLayoutManager(this)

        var list = ArrayList<String>()
        //todo: sciagniecie i umieszczenie listy skladnikow

        val myadapter = FindDishAdapter(list)
        recyclerview.adapter = myadapter

    }

    //dodanie menu kontekstowego
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        var item = menu.findItem(R.id.action_clean)
        item.setVisible(false)
        var self = menu.findItem(R.id.action_finddish)
        self.setVisible(false)

        menu.findItem(R.id.action_confirm).setVisible(true)
        return true
    }

    //menu kontekstowe
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_main -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
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
            R.id.action_about -> {
                showAboutDialog()
                true
            }
            R.id.action_confirm -> {
                //todo: wyszukiwanie potrawy po skladnikach
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
        builder.setMessage(
            Html.fromHtml("<b>Wersja 0.4<br><br>Autorzy:</b><br><i> " +
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


}