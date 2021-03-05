package com.example.TMDB.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.TMDB.R
import com.example.TMDB.adapter.FavouriteListAdapter
import com.example.TMDB.database.AppDb
import com.example.myapplication.MovieEntity
import kotlinx.android.synthetic.main.activity_favourite_list.*
import kotlinx.coroutines.launch
import android.os.AsyncTask
import android.util.Log


class FavouriteListActivity : AppCompatActivity() {
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var detailsAdapter: FavouriteListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL,
            false
        )
        lifecycleScope.launch { // coroutine on Main
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_favourite_list)

            recycler_fav_movie_list.layoutManager = layoutManager

            detailsAdapter = FavouriteListAdapter(baseContext, getFavList())
            detailsAdapter.notifyDataSetChanged()
            recycler_fav_movie_list.adapter = detailsAdapter
        }
    }

    private suspend fun getFavList(): List<MovieEntity> {
        val db = Room.databaseBuilder(applicationContext, AppDb::class.java, "MovieDB").build()
        return db.movieDao().getAllFavMovie()
    }
}