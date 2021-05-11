package com.kanyideveloper.rickymortyapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kanyideveloper.rickymortyapi.ui.CharacterPagingSource
import com.kanyideveloper.rickymortyapi.model.CharacterData
import com.kanyideveloper.rickymortyapi.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    fun getListData() : Flow<PagingData<CharacterData>> {
        return Pager(config = PagingConfig(34, 200),
        pagingSourceFactory = { CharacterPagingSource(mainRepository.getCharacter()) }).flow.cachedIn(viewModelScope)
    }
}