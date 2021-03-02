package com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.data

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("movie?sort_by=popularity.desc&api_key=3fa9058382669f72dcb18fb405b7a831")
    fun getNews(
            @Query("page") page: Int
    ): Single<Response>

    companion object {
        fun getService(): NetworkService {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/discover/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(NetworkService::class.java)

        }
    }

}