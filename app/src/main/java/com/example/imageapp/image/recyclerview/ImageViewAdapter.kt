package com.example.imageapp.image.recyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imageapp.R
import com.example.imageapp.image.retrofit.Data

class ImageViewAdapter(val context : Context) : RecyclerView.Adapter<ImageViewAdapter.ImageViewHolder>() {

    var itemList = listOf<Data>()
    set(value) {
        val diffUtil = ImageDiffUtil(field, value)
        val diffUtilResults = DiffUtil.calculateDiff(diffUtil)
        field = value
        diffUtilResults.dispatchUpdatesTo(this)
//        field = value
//        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {

        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.rv_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = itemList[position]
        val url = item.imageHref
        Glide.with(context).load(url).into(holder.itemImage)
        holder.itemTitle.text = item.imageName
        holder.itemView.setOnClickListener {
            Toast.makeText(context, item.imageDescription, Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ImageViewHolder(view : View) : RecyclerView.ViewHolder(view) {

//        var itemImage = view.findViewById<ImageView>(R.id.item_image)
        var itemTitle = view.findViewById<TextView>(R.id.item_title)
        val itemImage = view.findViewById<ImageView>(R.id.item_image)
    }

//    fun setData(newItemList: List<Data>) {
//        val diffUtil = ImageDiffUtil(itemList, newItemList)
//        val diffUtilResults = DiffUtil.calculateDiff(diffUtil)
//        itemList = newItemList
//        diffUtilResults.dispatchUpdatesTo(this)
//    }

}