package com.itsector.unsplash.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.itsector.unsplash.R
import com.itsector.unsplash.adapter.PhotosAdapter
import com.itsector.unsplash.data.entities.PhotoEntity
import com.itsector.unsplash.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), BaseFragment.OnBackPressedListener {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PhotosAdapter { photoEntity ->

            initFragment(photoEntity)
        }

        binding.rvPhotos.adapter = adapter
        binding.rvPhotos.layoutManager = GridLayoutManager(this, 2)

        lifecycleScope.launchWhenCreated {
            viewModel.listPhotos.collectLatest { photos ->
                adapter.submitData(photos)
            }
        }
    }

    private fun initFragment(photoEntity: PhotoEntity) {
        val fragment: Fragment? = supportFragmentManager.findFragmentByTag(PhotoDetailFragment::class.java.simpleName)

        if (fragment !is PhotoDetailFragment) {
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.container_main,
                    PhotoDetailFragment.newInstance(photoEntity.id, photoEntity.urls?.small, photoEntity.description),
                    PhotoDetailFragment::class.java.simpleName
                )
                .addToBackStack(null)
                .commit()

            binding.rvPhotos.visibility = View.GONE
        }
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.container_main)

        if (fragment is PhotoDetailFragment) {
            supportFragmentManager.popBackStack()
            binding.rvPhotos.visibility = View.VISIBLE
        } else {
            super.onBackPressed()
        }
    }
}

