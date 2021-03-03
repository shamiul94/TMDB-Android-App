package com.example.TMDB.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.TMDB.R
import com.example.TMDB.data.Details
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cast_item.view.*

class MovieDetailsAdapter(private val context: Context, private val movieDetails: Details) :
    RecyclerView.Adapter<MovieDetailsAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.cast_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return movieDetails.cast.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load("https://image.tmdb.org/t/p/original/" + movieDetails.cast[position].profilePath)
            .into(holder.image)
        holder.txt_name.text = movieDetails.cast[position].name
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var txt_name: TextView

        init {
            image = itemView.image_movie
            txt_name = itemView.txt_name
        }
    }
}