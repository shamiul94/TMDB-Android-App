package com.example.myapplication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDAO
{
    @Insert
    fun saveMovie(movie: MovieEntity)


    @Query(value = "Select * from MovieEntity")
    fun getAllFavMovie() : List<MovieEntity>
}