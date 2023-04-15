package com.itsector.unsplash.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.itsector.unsplash.R
import com.itsector.unsplash.data.entities.PhotoEntity
import com.itsector.unsplash.databinding.ItemPhotoBinding
import com.squareup.picasso.Picasso

class PhotosAdapter(
    private val onPhotoClickListener: (PhotoEntity) -> Unit
) : PagingDataAdapter<PhotoEntity, PhotosAdapter.PhotosViewHolder>(PHOTO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        return PhotosViewHolder(
            ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onPhotoClickListener
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

            override fun areContentsTheSame(oldItem: PhotoEntity, newItem: PhotoEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class PhotosViewHolder(
        private val binding: ItemPhotoBinding,
        private val onPhotoClickListener: (PhotoEntity) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PhotoEntity) {

            binding.ivPhoto.apply {
                item.urls?.small?.let { Picasso.get().load(it).into(this) }
            }

            binding.cvPhoto.setOnClickListener { onPhotoClickListener(item) }

            binding.tvLikes.text = item.likes.toString()
        }
    }
}


