package com.kanyideveloper.rickymortyapi.repository

import com.kanyideveloper.rickymortyapi.model.RickyMortyCharacter
import com.kanyideveloper.rickymortyapi.network.RetrofitService
import javax.inject.Inject

class MainRepository @Inject constructor(private val retrofitService: RetrofitService) {

    suspend fun getCharacter(page: Int) : RickyMortyCharacter{
        return retrofitService.getCharacters(page)
    }
}