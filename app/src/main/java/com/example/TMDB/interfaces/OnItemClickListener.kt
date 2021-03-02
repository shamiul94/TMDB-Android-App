package com.example.TMDB.interfaces

import com.example.TMDB.data.News

interface OnItemClickListener {
    fun onItemClicked(news: News)
}