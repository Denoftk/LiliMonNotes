package com.HunterSoul.notas.data

import com.HunterSoul.notas.repository.NotasApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface  AppContainer{
    val notasRepository: NotasRepository
}

class DefaultAppContainer : AppContainer {
    private val BASE_URL =
        "https://e490-187-251-133-194.ngrok-free.app"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: NotasApiService by lazy {
        retrofit.create(NotasApiService::class.java)
    }

    override val notasRepository: NotasRepository by lazy {
        LilNotasRepository(retrofitService)
    }

}