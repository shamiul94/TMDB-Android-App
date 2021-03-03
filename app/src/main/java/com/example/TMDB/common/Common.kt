package com.example.TMDB.common

import com.example.TMDB.Retrofit.RetrofitClient
import com.example.TMDB.interfaces.RetrofitService


object Common {
    private val BASE_URL = "https://api.themoviedb.org/3/movie/"

    val retrofitService: RetrofitService
    get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}