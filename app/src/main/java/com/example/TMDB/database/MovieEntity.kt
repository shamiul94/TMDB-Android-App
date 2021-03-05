package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MovieEntity {

    @PrimaryKey
    var id: Int = 0

    @ColumnInfo(name = "movie_id")
    var movieId: Int = 0

    @ColumnInfo(name = "movie_name")
    var movieName: String = ""

}