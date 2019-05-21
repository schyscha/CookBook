package com.example.cookbook

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cookbook.database.CompleteRecipe
import java.lang.StringBuilder


class DishAdapter(var list:ArrayList<CompleteRecipe>): RecyclerView.Adapter<DishAdapter.ViewHolder>() {

    fun setDishes(list: ArrayList<CompleteRecipe>) {
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
        fun bindItems(data : CompleteRecipe){
            val _nazwa: TextView = itemView.findViewById(R.id.nazwa)
            val _tagi: TextView = itemView.findViewById(R.id.tagi)
            val _img: ImageView = itemView.findViewById(R.id.img)

            _nazwa.text = data.recipe.name

            val tags= StringBuilder()
            tags.append(data.tags.first().name)
            for(i in 1..data.tags.size){
                tags.append(", ")
                tags.append(data.tags.get(i).name)
            }
            _tagi.text = tags.toString()

            Glide.with(this.itemView).load(data.recipe.image_urls.first()).into(_img);

            //set the onclick listener for the single list item
            itemView.setOnClickListener({
                val id : Long = data.recipe.id
                val myintent = Intent(it.context, Dish::class.java )
                myintent.putExtra("id", id)
            })
        }

    }
}
