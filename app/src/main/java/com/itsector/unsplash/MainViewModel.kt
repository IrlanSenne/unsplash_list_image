package com.itsector.unsplash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.itsector.unsplash.api.entities.PhotoEntity
import com.itsector.unsplash.data.MainRepository
import com.itsector.unsplash.data.PER_PAGE
import com.itsector.unsplash.data.PhotosPagingSource
import kotlinx.coroutines.flow.Flow

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    val listPhotos: Flow<PagingData<PhotoEntity>> = Pager(PagingConfig(pageSize = PER_PAGE)) {
        PhotosPagingSource(repository)
    }.flow.cachedIn(viewModelScope)
}
