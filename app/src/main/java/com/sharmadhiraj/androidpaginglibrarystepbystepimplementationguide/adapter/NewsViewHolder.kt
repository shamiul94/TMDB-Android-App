package com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.R
import com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.data.News
import com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.interfaces.OnItemClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news.view.*

class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(news: News?, clickListener: OnItemClickListener) {
        if (news != null) {
            itemView.txt_news_name.text = news.title
            if (news.posterPath.isNotEmpty())
                Log.d("poster path:: ", news.posterPath)
            Picasso.get().load("https://image.tmdb.org/t/p/w500" + news.posterPath)
                .into(itemView.img_news_banner)

            itemView.setOnClickListener {
                clickListener.onItemClicked(news)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): NewsViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news, parent, false)
            return NewsViewHolder(view)
        }
    }
}