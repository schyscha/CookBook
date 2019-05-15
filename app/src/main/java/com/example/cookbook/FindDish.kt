package com.example.cookbook

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.beardedhen.androidbootstrap.TypefaceProvider
import kotlinx.android.synthetic.main.activity_tobuy.*
import java.util.*

class FindDish : MyActivity() {


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
        super.onCreateOptionsMenu(menu)
        menu.findItem(R.id.action_clean).isVisible = false
        menu.findItem(R.id.action_finddish).isVisible = false
        menu.findItem(R.id.action_confirm).isVisible = true
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        if (item.itemId == R.id.action_confirm){
            //todo: zatwierdzanie wybrania skladnikow z listy
        }
        return true
    }


}