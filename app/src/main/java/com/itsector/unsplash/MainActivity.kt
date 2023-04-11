package com.itsector.unsplash

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.itsector.unsplash.adapter.BaseAdapter
import com.itsector.unsplash.adapter.OnSelectListener
import com.itsector.unsplash.adapter.viewholder.PhotosViewHolder
import com.itsector.unsplash.api.entities.PhotosEntity
import com.itsector.unsplash.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvPhotos.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launchWhenCreated {
            viewModel.listPhotos.collectLatest { photos ->
                binding.rvPhotos.adapter = BaseAdapter { viewGroup ->
                    PhotosViewHolder(viewGroup)
                }.apply {
                    items = photos?.toMutableList() ?: mutableListOf(PhotosEntity())
                }
            }
        }
    }
}