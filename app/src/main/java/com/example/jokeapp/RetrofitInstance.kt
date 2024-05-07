package com.example.jokeapp

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private var BASE_URL = "https://qoa6sfaa93.execute-api.ap-south-1.amazonaws.com/dev/"
    private var token: String? = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjdXN0b206X2lkIjoiNjVjNWU5ODJmYjhhMTAzMWE1MzIyZjMzIiwidXNlcm5hbWUiOiJCYWJ1QDEyMyIsImN1c3RvbTpyb2xlIjoic3R1ZGVudCIsImJhdGNoSWQiOiI2NWFiOTg0ODYwMWY5N2U1MGIwOTQ2NGQiLCJpYXQiOjE3MTUwNjY4MjUsImV4cCI6MTcxNTE1MzIyNX0.NNrhKJcyWbWQcBIyfV_01duqsmWzjzjGz_Ps9M5-kjE"

    private fun getHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val headerInterceptor = Interceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
            token?.let {
                requestBuilder.header("Authorization", "Bearer $it")
                    .build()
            }

            val request = requestBuilder.build()
            chain.proceed(request)
        }
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.MINUTES)
            .addInterceptor(interceptor)
            .addInterceptor(headerInterceptor)
            .build()
    }

    fun getRetrofitInstance(baseUrl: String? = null): ApiInterface {
        baseUrl?.let {
            BASE_URL = it
        }
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getHttpClient())
            .build()

        return retrofit.create(ApiInterface::class.java)
    }

}
