package com.itsector.unsplash.adapter.viewholder

import android.view.ViewGroup
import android.widget.TextView
import com.itsector.unsplash.R
import com.itsector.unsplash.api.entities.PhotosEntity

class PhotosViewHolder(viewGroup: ViewGroup) : BaseViewHolder<PhotosEntity?>(R.layout.item_photo, viewGroup) {
    override fun bind(item: PhotosEntity?) {
        val itemView = itemView.findViewById<TextView>(R.id.tv_test)


        itemView.text = item?.urls?.small ?: ""

    }
}