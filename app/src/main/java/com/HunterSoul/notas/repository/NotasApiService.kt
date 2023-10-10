package com.HunterSoul.notas.repository

import com.HunterSoul.notas.model.Nota
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
//import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://e490-187-251-133-194.ngrok-free.app"



private val retrofit = Retrofit.Builder()
    .addConverterFactory(
        Json.asConverterFactory("application/json".toMediaType())
    )
    .baseUrl(BASE_URL)
    .build()

interface NotasApiService {
    @GET("notas")
    suspend fun getAllNotas() : List<Nota>
}