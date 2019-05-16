package com.example.cookbook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class DishAdapter(var list:ArrayList<Dish>): RecyclerView.Adapter<DishAdapter.ViewHolder>() {

    fun setDishes(list: ArrayList<Dish>) {
        this.list = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_dish, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: DishAdapter.ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindItems(data : Dish){
            val _nazwa: TextView = itemView.findViewById(R.id.nazwa)
            val _tagi: TextView = itemView.findViewById(R.id.tagi)
            _nazwa.text = data.nazwa
            _tagi.text = android.text.TextUtils.join(",", data.tagi)
            //todo: wstawianie obrazka (po ogarnięciu biblioteki do pokazywania)

            //set the onclick listener for the single list item
            itemView.setOnClickListener({
                //todo: przekierowanie do aktywnosci ODPOWIEDNIEJ potrawy z listy(bazy)
            })
        }

    }
}
