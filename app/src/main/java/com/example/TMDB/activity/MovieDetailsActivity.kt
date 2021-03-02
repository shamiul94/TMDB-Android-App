package com.example.TMDB.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.example.TMDB.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news.view.*

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var movie_title: String
    private lateinit var back_drop_path: String
    private lateinit var poster_path: String
    private lateinit var details: String
    private lateinit var vote_average: String
    private lateinit var release_date: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        initiateTextFields()
    }

    private fun initiateTextFields() {
        movie_title = intent.getStringExtra("movie_title").toString()
        back_drop_path = intent.getStringExtra("back_drop_path").toString()
        poster_path = intent.getStringExtra("poster_path").toString()
        details = intent.getStringExtra("details").toString()
        vote_average = intent.getStringExtra("vote_average").toString()
        release_date = intent.getStringExtra("release_date").toString()

        Log.d("vote_average", vote_average)
        Log.d("movie_title", movie_title)
        Log.d("back_drop_path", back_drop_path)
        Log.d("details", details)
        Log.d("poster_path", poster_path)

        val overview_field: TextView = findViewById<TextView>(R.id.details_overview)
        val title_field: TextView = findViewById<TextView>(R.id.details_movie_title)
        val rating_star: RatingBar = findViewById(R.id.rating_bar)
        val rating_text: TextView = findViewById(R.id.rating_text)
        val poster_image: ImageView = findViewById(R.id.details_poster)
        val cover_image: ImageView = findViewById(R.id.details_cover)
        val release_date_text: TextView = findViewById(R.id.release_date)

        val a: Double = vote_average.toFloat() / 2.0
        overview_field.text = details
        title_field.text = movie_title
        rating_star.rating = a.toFloat()
        rating_text.text = vote_average
        release_date_text.text = release_date

        Picasso.get().load("https://image.tmdb.org/t/p/original" + poster_path)
            .into(poster_image)

        Picasso.get().load("https://image.tmdb.org/t/p/original" + back_drop_path)
            .into(cover_image)
    }
}