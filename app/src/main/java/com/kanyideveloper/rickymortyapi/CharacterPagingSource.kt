package com.kanyideveloper.rickymortyapi

import android.net.Uri
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kanyideveloper.rickymortyapi.model.CharacterData
import com.kanyideveloper.rickymortyapi.repository.MainRepository
import java.lang.Exception
import javax.inject.Inject

class CharacterPagingSource @Inject constructor(private val mainRepository: MainRepository) : PagingSource<Int, CharacterData>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterData>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterData> {
        return try {
            val nextPage : Int = params.key ?: FIRST_PAGE_INDEX
            val response = mainRepository.getCharacter(nextPage)
            Log.d("MainViewModel", "load: ${response.info}")
            var nextPageNumber : Int? = null

            if (response.info.next != null){
                val uri = Uri.parse(response.info.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }

            LoadResult.Page(data = response.results, prevKey = null, nextKey = nextPageNumber)

        }catch(e: Exception){
            LoadResult.Error(e)
        }
    }

    companion object{
        private const val FIRST_PAGE_INDEX = 1
    }
}