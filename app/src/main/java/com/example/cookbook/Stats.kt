package com.example.cookbook

import android.os.Bundle
import android.view.Menu
import com.beardedhen.androidbootstrap.TypefaceProvider

class Stats : MyActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)
        TypefaceProvider.registerDefaultIconSets();

    }

    //dodanie menu kontekstowego
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menu.findItem(R.id.action_stats).isVisible = false
        menu.findItem(R.id.action_clean).isVisible = false
        return true
    }

}