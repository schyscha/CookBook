package com.example.cookbook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.single_image.view.*

class ImageAdapter(var list:ArrayList<String>): RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.single_image, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }
    
    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bindItems(url: String){
            Glide.with(view).load(url).centerCrop().into(view.single_image)
        }

    }
}