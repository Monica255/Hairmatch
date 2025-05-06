package com.example.hairmatch.core.data.repository

import com.example.hairmatch.core.data.Resource
import com.example.hairmatch.core.data.remote.ApiService
import com.example.hairmatch.core.data.remote.model.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(private val apiService: ApiService) {
    fun upload(data:MultipartBody.Part): Flow<Resource<Response>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = apiService.uploadImage(data)
                emit(Resource.Success(response))
            } catch (e : Exception){
                emit(Resource.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}