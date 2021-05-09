package com.kanyideveloper.rickymortyapi.di

import com.kanyideveloper.rickymortyapi.network.RetrofitService
import com.kanyideveloper.rickymortyapi.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)

@Module
object RepositoryModule  {

    @Provides
    fun providesMainRepository(retrofitService: RetrofitService) : MainRepository{
        return MainRepository(retrofitService)
    }
}