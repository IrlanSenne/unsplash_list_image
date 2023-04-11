package com.itsector.unsplash.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.itsector.unsplash.R
import com.itsector.unsplash.api.entities.PhotoEntity
import com.itsector.unsplash.databinding.ItemPhotoBinding
import com.squareup.picasso.Picasso

class PhotosAdapter : PagingDataAdapter<PhotoEntity, PhotosAdapter.PhotosViewHolder>(PHOTO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        return PhotosViewHolder(
            ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<PhotoEntity>() {
            override fun areItemsTheSame(oldItem: PhotoEntity, newItem: PhotoEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: PhotoEntity,
                newItem: PhotoEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    class PhotosViewHolder(binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PhotoEntity) {
            val itemView = itemView.findViewById<ImageView>(R.id.iv_photo)

            item.urls?.small?.let {
                Picasso.get().load(it).into(itemView)
            }
        }
    }
}
