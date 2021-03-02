package com.example.TMDB.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        val overview_field: TextView = findViewById<TextView>(R.id.details_overview)
        val title_field: TextView = findViewById<TextView>(R.id.details_movie_title)

        overview_field.text = details
        title_field.text = movie_title
    }
}