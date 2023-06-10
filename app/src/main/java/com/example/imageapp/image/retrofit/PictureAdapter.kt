package com.example.pictures.forRV

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imageapp.R
import com.example.imageapp.image.retrofit.Data

class PictureAdapter(var context: Context, var list : List<Data>) : RecyclerView.Adapter<PictureAdapter.ImageViewHolder>(){

    inner class ImageViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.item_image)
        val name = view.findViewById<TextView>(R.id.item_title)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.rv_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Glide.with(context).load(list[position].imageHref).into(holder.image)

        holder.name.text = list[position].imageName
    }
}