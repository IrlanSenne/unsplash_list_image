package com.itsector.unsplash.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.itsector.unsplash.data.entities.PhotoEntity
import com.itsector.unsplash.data.repository.MainRepository
import com.itsector.unsplash.data.repository.PER_PAGE
import com.itsector.unsplash.data.paging.PhotosPagingSource
import kotlinx.coroutines.flow.Flow

class MainViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {

    val listPhotos: Flow<PagingData<PhotoEntity>> = Pager(PagingConfig(pageSize = PER_PAGE)) {
        PhotosPagingSource(mainRepository)
    }.flow.cachedIn(viewModelScope)


}
