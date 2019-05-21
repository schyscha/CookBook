package com.example.cookbook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.beardedhen.androidbootstrap.BootstrapButton
import com.beardedhen.androidbootstrap.TypefaceProvider


class ToBuyAdapter(val list:ArrayList<String>, val db:TinyDB): RecyclerView.Adapter<ToBuyAdapter.ViewHolder>() {

    public lateinit var myparent: ViewGroup

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToBuyAdapter.ViewHolder {
        myparent = parent
        TypefaceProvider.registerDefaultIconSets();
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_ingredient, parent, false)
        return ViewHolder(v, myparent)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ToBuyAdapter.ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(view: View, val nextparent: ViewGroup): RecyclerView.ViewHolder(view) {
        fun bindItems(data : String){
            val element: TextView = itemView.findViewById(R.id.txt)
            element.text = data


            //set the onclick listener for the single list item
            val btn: BootstrapButton = itemView.findViewById(R.id.button_delete_ingredient)
            btn.setOnClickListener({
                (nextparent.context as ToBuy).list.remove(data)
                (nextparent.context as ToBuy).refresh()
                //todo: to castowanie wyzej nie dziala...

            })
        }

    }
}
