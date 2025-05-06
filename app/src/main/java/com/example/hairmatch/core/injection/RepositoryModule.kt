package com.example.hairmatch.core.injection

import com.example.hairmatch.core.data.remote.ApiService
import com.example.hairmatch.core.data.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository(apiService: ApiService): MainRepository{
        return MainRepository(apiService)
    }

}