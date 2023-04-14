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
import com.itsector.unsplash.data.repository.TrackRegisterUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainRepository: MainRepository,
    private val setTrackRegister: TrackRegisterUseCase
) : ViewModel() {

    val listPhotos: Flow<PagingData<PhotoEntity>> = Pager(PagingConfig(pageSize = PER_PAGE)) {
        PhotosPagingSource(mainRepository)
    }.flow.cachedIn(viewModelScope)


    fun trackRegister(id: String) {
        viewModelScope.launch {
            print("trackRegister")
            setTrackRegister.trackRegister(id)
        }
    }
}
