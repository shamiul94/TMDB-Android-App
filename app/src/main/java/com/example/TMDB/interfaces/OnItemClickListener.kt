package com.example.TMDB.interfaces

import com.example.TMDB.data.Movies

interface OnItemClickListener {
    fun onItemClicked(movies: Movies)
}