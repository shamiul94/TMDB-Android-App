package com.example.TMDB.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.TMDB.R
import com.example.TMDB.data.Movies
import com.example.TMDB.interfaces.OnItemClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news.view.*

class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(movies: Movies?, clickListener: OnItemClickListener) {
        if (movies != null) {
            itemView.txt_news_name.text = movies.title
            if (movies.posterPath.isNotEmpty())
                Log.d("poster path:: ", movies.posterPath)
            Picasso.get().load("https://image.tmdb.org/t/p/w500" + movies.posterPath)
                .into(itemView.img_news_banner)

            itemView.setOnClickListener {
                clickListener.onItemClicked(movies)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): MoviesViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news, parent, false)
            return MoviesViewHolder(view)
        }
    }
}