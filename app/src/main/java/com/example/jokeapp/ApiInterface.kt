package com.example.jokeapp

import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiInterface {

    @GET("quiz/student/get-quiz?")
    suspend fun getData(
        @Query("gradeId") gradeId: String,
        @Query("batchId") batchId: String,
        @Query("quizId") quizId: String

    ): Response<responceDataClass>
}



