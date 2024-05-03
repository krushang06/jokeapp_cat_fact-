package com.example.jokeapp

import com.example.mytoasty.AppMaticNetworkInterceptorr
import com.shakebugs.shake.Shake
import com.shakebugs.shake.Shake.insertNetworkRequest
import com.shakebugs.shake.network.NetworkRequestBuilder
import com.shakebugs.shake.network.ShakeNetworkInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Date

object RetrofitInstance {
    //    private const val BASE_URL = "hps://official-joke-api.appspot.comtt/"
    private var baseUrl = "https://catfact.ninja/"
    fun getRetrofitInstance(): ApiInterface {

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AppMaticNetworkInterceptorr)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl) // Use dynamic base URL
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiInterface::class.java)
    }
}



/*object RetrofitInstance {
    // original app
    private const val BASE_URL = "https://catfact.ninja/"

    fun getRetrofitInstance(): ApiInterface {

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AppMaticNetworkInterceptorr)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiInterface::class.java)
    }
}*/


//object RetrofitInstance {
//
//    private const val BASE_URL = "https://catfact.ninja/"
//
//    fun getRetrofitInstance(): ApiInterface {
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(OkHttpClient())  // Use the custom OkHttpClient
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        return retrofit.create(ApiInterface::class.java)
//    }
//    fun HttpLoggingInterceptor(): OkHttpClient {
//        var httpLoggingInterceptor = HttpLoggingInterceptor()
//        httpLoggingInterceptor = HttpLoggingInterceptor()
//        return httpLoggingInterceptor
//    }
//}

