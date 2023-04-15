package com.itsector.unsplash.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.itsector.unsplash.R
import com.squareup.picasso.Picasso


private const val ID_PHOTO = "id_photo"
private const val URL_PHOTO = "url_photo"
private const val DESCRIPTION_PHOTO = "description_photo"

class PhotoDetailFragment : BaseFragment() {
    private var id: String? = null
    private var photoUrl: String? = null
    private var description: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString(ID_PHOTO)
            photoUrl = it.getString(URL_PHOTO)
            description = it.getString(DESCRIPTION_PHOTO)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_photo_detail, container, false)
        val imageView = view.findViewById<ImageView>(R.id.iv_photo_detail)

        val toolbar: Toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.title = description

        toolbar.setNavigationOnClickListener {
            onBackButtonClicked()
        }

        id?.let { viewModel.trackRegister(it) }

        photoUrl?.let { Picasso.get().load(it).into(imageView) }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnBackPressedListener) {
            onBackPressedListener = context
        } else {
            throw RuntimeException("$context")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(id: String?, url: String?, description: String?) =
            PhotoDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ID_PHOTO, id)
                    putString(URL_PHOTO, url)
                    putString(DESCRIPTION_PHOTO, description)
                }
            }
    }
}

