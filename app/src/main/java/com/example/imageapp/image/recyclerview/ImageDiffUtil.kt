package com.example.imageapp.image.recyclerview

import androidx.recyclerview.widget.DiffUtil

class ImageDiffUtil(
    val oldList : List<com.example.imageapp.image.retrofit.Data>,
    val newList: List<com.example.imageapp.image.retrofit.Data>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].imageId == newList[newItemPosition].imageId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].imageName != newList[newItemPosition].imageName -> {
                false
            }
            oldList[oldItemPosition].imageDescription != newList[newItemPosition].imageDescription -> {
                false
            }
            oldList[oldItemPosition].imageHref != newList[newItemPosition].imageHref -> {
                false
            }
            else -> true

        }
    }
}