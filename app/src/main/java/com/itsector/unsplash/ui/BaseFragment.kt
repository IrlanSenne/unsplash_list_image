package com.itsector.unsplash.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.itsector.unsplash.R
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class BaseFragment : Fragment() {
    val viewModel by viewModel<MainViewModel>()
    protected var onBackPressedListener: OnBackPressedListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    protected fun onBackButtonClicked() {
        onBackPressedListener?.onBackPressed()
    }

    interface OnBackPressedListener {
        fun onBackPressed()
    }
}
