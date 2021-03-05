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
import com.example.myapplication.MovieEntity
import kotlinx.android.synthetic.main.cast_item.view.*
import kotlinx.android.synthetic.main.fav_list_item.view.*

class FavouriteListAdapter(private val context: Context, private val favMovieList: List<MovieEntity>) :
    RecyclerView.Adapter<FavouriteListAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.fav_list_item, parent, false)
        return FavouriteListAdapter.MyViewHolder(itemView)
    }



    override fun getItemCount(): Int {
        return favMovieList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txt_name: TextView

        init {
            txt_name = itemView.fav_movie_name
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txt_name.text = favMovieList[position].movieName
    }
}