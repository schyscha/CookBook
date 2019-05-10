package com.example.cookbook

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.beardedhen.androidbootstrap.TypefaceProvider
import kotlinx.android.synthetic.main.activity_tobuy.*
import java.util.ArrayList

class ToBuy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tobuy)
        TypefaceProvider.registerDefaultIconSets();

        recyclerview.layoutManager = LinearLayoutManager(this)

        var list = ArrayList<String>()
        //todo: sciagniecie bazy i umieszczenie jej elementow jako String w list

        val myadapter = ToBuyAdapter(list)
        recyclerview.adapter = myadapter

    }

    //todo: dodawanie do bazy listy zakupow
    fun addToBuy(view: View){

    }
}