package com.example.TMDB.data


import com.google.gson.annotations.SerializedName

data class Details(
    @SerializedName("cast")
    val cast: MutableList<Cast>,
    @SerializedName("crew")
    val crew: MutableList<Crew>,
    @SerializedName("id")
    val id: Int
)