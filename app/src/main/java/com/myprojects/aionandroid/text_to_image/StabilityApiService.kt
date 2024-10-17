package com.myprojects.aionandroid.text_to_image

import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Header

interface StabilityApiService {

    @Multipart
    @POST("v1/generation")
    suspend fun generateImage(
        @Header("Authorization") apiKey: String,
        @Part image: MultipartBody.Part?,
        @Part("params") params: Map<String, String>
    ): Response<ResponseBody>
}

object RetrofitClient {
    private const val BASE_URL = "https://api.stability.ai/"

    private val client = OkHttpClient.Builder().build()

    val instance: StabilityApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(StabilityApiService::class.java)
    }
}

