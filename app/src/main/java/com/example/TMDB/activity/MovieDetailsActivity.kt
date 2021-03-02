package com.example.TMDB.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RatingBar
import android.widget.TextView
import com.example.TMDB.R

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var movie_title: String
    private lateinit var back_drop_path: String
    private lateinit var poster_path: String
    private lateinit var details: String
    private lateinit var vote_average: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        movie_title = intent.getStringExtra("movie_title").toString()
        back_drop_path = intent.getStringExtra("back_drop_path").toString()
        poster_path = intent.getStringExtra("poster_path").toString()
        details = intent.getStringExtra("details").toString()
        vote_average = intent.getStringExtra("vote_average").toString()

        Log.d("vote_average", vote_average)
        Log.d("movie_title", movie_title)
        Log.d("back_drop_path", back_drop_path)
        Log.d("details", details)
        Log.d("poster_path", poster_path)

        val overview_field: TextView = findViewById<TextView>(R.id.details_overview)
        val title_field: TextView = findViewById<TextView>(R.id.details_movie_title)
        val rating_star: RatingBar = findViewById(R.id.rating_bar)
        val rating_text : TextView = findViewById(R.id.rating_text)

        overview_field.text = details
        title_field.text = movie_title
        rating_star.setRating(vote_average.toFloat())
        rating_text.text = vote_average
    }
}