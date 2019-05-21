package com.example.cookbook

import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.beardedhen.androidbootstrap.TypefaceProvider
import kotlinx.android.synthetic.main.activity_tobuy.*

class ToBuy : MyActivity() {
    lateinit var list : ArrayList<String>
    lateinit var db: TinyDB
    lateinit var myadapter : ToBuyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tobuy)
        TypefaceProvider.registerDefaultIconSets();

        recyclerview.layoutManager = LinearLayoutManager(this)
        db = TinyDB(this)

        list = db.getListString("TOBUY")
        if(list.isEmpty())
            Toast.makeText(this,"Twoja lista zakupów jest pusta", Toast.LENGTH_LONG).show();

        myadapter = ToBuyAdapter(list, db)
        recyclerview.adapter = myadapter

    }

    fun refresh(){
        myadapter.notifyDataSetChanged()
        db.putListString("TOBUY", list)
    }

    fun addToBuy(view: View){
        lateinit var search: String
        lateinit var dialog : AlertDialog
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Wprowadź przedmiot")

        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        builder.setPositiveButton(
            "Dodaj"
        ) { dialog, which -> if(input.text.toString().isNotEmpty())list.add(input.text.toString()); db.putListString("TOBUY", list)}
        builder.setNegativeButton(
            "Anuluj"
        ) { dialog, which -> dialog.cancel() }

        dialog = builder.create()
        dialog.show()
    }

    //dodanie menu kontekstowego
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menu.findItem(R.id.action_tobuy).isVisible = false
        return true
    }

    override fun clean() {
        list.clear()
        refresh()
    }
}