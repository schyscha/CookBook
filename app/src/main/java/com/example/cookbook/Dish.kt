package com.example.cookbook

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import com.beardedhen.androidbootstrap.TypefaceProvider

data class Dish(
    var nr: Int,
    var nazwa: String,
    var ocena: Float,
    var linki: Array<String>,
    var tagi: Array<String>,
    var skladniki: Array<String>,
    var sposob: String
) : MyActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish)
        TypefaceProvider.registerDefaultIconSets();

    }

    //funkcja usuwająca potrawę z bazy
    fun delete(view: View){
        //todo: funkcja usuwajaca potrawe z bazy(listy)
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