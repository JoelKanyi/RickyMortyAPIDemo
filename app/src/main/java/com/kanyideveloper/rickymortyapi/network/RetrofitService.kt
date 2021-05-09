package com.kanyideveloper.rickymortyapi.network

import com.kanyideveloper.rickymortyapi.model.RickyMortyCharacter
import com.kanyideveloper.rickymortyapi.utils.Constants.END_POINT
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET(END_POINT)
    suspend fun getCharacters(@Query("page") query: Int) : RickyMortyCharacter
}