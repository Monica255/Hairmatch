package com.example.hairmatch.core.data.remote

import com.example.hairmatch.core.data.remote.model.Response
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @Multipart
    @POST("/predict")
    suspend fun uploadImage(
        @Part image: MultipartBody.Part,
    ): Response
}