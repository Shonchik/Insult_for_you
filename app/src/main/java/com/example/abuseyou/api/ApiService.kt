package com.example.abuseyou.api

import com.example.abuseyou.api.model.InsultModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("generate_insult.php")
    suspend fun getInsult(
        @Query("lang") lang: String,
        @Query("type") type: String,
    ): InsultModel

}