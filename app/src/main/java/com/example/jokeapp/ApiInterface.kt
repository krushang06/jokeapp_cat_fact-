package com.example.jokeapp

import retrofit2.http.GET
import retrofit2.Response

interface ApiInterface {
//    @GET("random_joke")
    @GET("fact")
    suspend fun getData(): Response<responceDataClass2>
}
