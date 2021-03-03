package com.example.TMDB.interfaces

import com.example.TMDB.data.Details
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    @GET("{movie_id}/credits?api_key=3fa9058382669f72dcb18fb405b7a831&language=en-US")
    fun getCredits(
        @Path(value = "movie_id", encoded = true) movie_id: String
    ): Call<Details>
}