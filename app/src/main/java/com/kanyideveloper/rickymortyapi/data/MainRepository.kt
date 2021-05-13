package com.kanyideveloper.rickymortyapi.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kanyideveloper.rickymortyapi.model.CharacterData
import com.kanyideveloper.rickymortyapi.network.RetrofitService
import com.kanyideveloper.rickymortyapi.data.CharacterPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepository @Inject constructor(private val retrofitService: RetrofitService) {

    fun getCharacter() : Flow<PagingData<CharacterData>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 30),
            pagingSourceFactory = {
                CharacterPagingSource(retrofitService)
            }
        ).flow
    }
}