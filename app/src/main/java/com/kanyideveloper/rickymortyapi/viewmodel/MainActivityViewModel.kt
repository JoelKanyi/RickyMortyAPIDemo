package com.kanyideveloper.rickymortyapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kanyideveloper.rickymortyapi.CharacterPagingSource
import com.kanyideveloper.rickymortyapi.model.CharacterData
import com.kanyideveloper.rickymortyapi.network.ResultData
import com.kanyideveloper.rickymortyapi.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    fun getListData() : Any {
        /*return Pager(config = PagingConfig(34, 200),
        pagingSourceFactory = {CharacterPagingSource(mainRepository)}).flow.cachedIn(viewModelScope)*/

        return flow {
            emit(ResultData.Loading())
            try {
                emit(Pager(config = PagingConfig(34, 200), pagingSourceFactory = {CharacterPagingSource(mainRepository)}).flow.cachedIn(viewModelScope))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(ResultData.Exception())
            }
        }.asLiveData(Dispatchers.IO)
    }

}

/*
*  fun getRepositories(): LiveData<ResultData<Opensource254ReposModel?>> {
        return flow {
            emit(ResultData.Loading())
            try {
                emit(dataRepository.getPublicRepositories())
            } catch (e: Exception) {
                e.printStackTrace()
                emit(ResultData.Exception())
            }
        }.asLiveData(Dispatchers.IO)
    }*/