package com.example.cookbook

import android.content.Intent
import android.text.Html
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

open class MyActivity : AppCompatActivity() {

    //dodanie menu kontekstowego
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
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
            R.id.action_myingredients -> {
                val intent = Intent(this, MyIngredients::class.java)
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
    open fun showAboutDialog(){
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
    open fun showSearchDialog(){
        lateinit var search: String
        lateinit var dialog : AlertDialog
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Wyszukaj potrawę")

        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        builder.setPositiveButton(
            "Wyszukaj"
        ) { dialog, which -> search = input.text.toString(); showResults()}//todo: wyszukiwanie, pokazywanie rezultatow
        builder.setNegativeButton(
            "Anuluj"
        ) { dialog, which -> dialog.cancel() }

        dialog = builder.create()
        dialog.show()
    }

    //okno dialogowe usuwania zawartosci listy z menu kontekstowego
    open fun showCleanDialog(){
        lateinit var dialog: AlertDialog

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Usunąć wszystkie pozycje?")

        builder.setMessage("Ta operacja nie może zostać cofnięta")


        builder.setPositiveButton(
            "Usuń"
        ) { dialog, which -> clean()}
        builder.setNegativeButton(
            "Anuluj"
        ) { dialog, which -> dialog.cancel() }

        dialog = builder.create()
        dialog.show()
    }

    open fun clean(){}

    //todo: wyszukiwanie
    fun showResults(){

    }

}