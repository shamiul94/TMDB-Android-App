package com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.interfaces

import com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.data.News

interface OnItemClickListener {
    fun onItemClicked(news: News)
}