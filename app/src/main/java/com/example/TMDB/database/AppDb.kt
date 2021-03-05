package com.example.TMDB.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.MovieDAO
import com.example.myapplication.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class AppDb : RoomDatabase() {

    abstract fun movieDao(): MovieDAO

}