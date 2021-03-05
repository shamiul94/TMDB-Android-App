package com.example.TMDB.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room.databaseBuilder
import com.example.TMDB.R
import com.example.TMDB.adapter.MovieDetailsAdapter
import com.example.TMDB.common.Common
import com.example.TMDB.data.Details
import com.example.TMDB.database.AppDb
import com.example.TMDB.interfaces.RetrofitService
import com.example.myapplication.MovieEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var movie_title: String
    private lateinit var back_drop_path: String
    private lateinit var poster_path: String
    private lateinit var details: String
    private lateinit var vote_average: String
    private lateinit var release_date: String
    private lateinit var movie_id: String

    lateinit var mService: RetrofitService
    lateinit var layoutManager: LinearLayoutManager
    lateinit var detailsAdapter: MovieDetailsAdapter
    lateinit var db: AppDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)


        initiateTextFields()

        mService = Common.retrofitService

//        recycler_cast_list.setHasFixedSize(false)
        layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.HORIZONTAL,
            false
        )
        recycler_cast_list.layoutManager = layoutManager


        getAllMovieList()
    }

    private fun getAllMovieList() {

        mService.getCredits(movie_id).enqueue(object : Callback<Details> {

            override fun onFailure(call: Call<Details>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<Details>, response: Response<Details>) {
                var x = response
                detailsAdapter = MovieDetailsAdapter(baseContext, response.body() as Details)
                detailsAdapter.notifyDataSetChanged()
                recycler_cast_list.adapter = detailsAdapter
            }

        })
    }

    private fun initiateTextFields() {
        movie_title = intent.getStringExtra("movie_title").toString()
        back_drop_path = intent.getStringExtra("back_drop_path").toString()
        poster_path = intent.getStringExtra("poster_path").toString()
        details = intent.getStringExtra("details").toString()
        vote_average = intent.getStringExtra("vote_average").toString()
        release_date = intent.getStringExtra("release_date").toString()
        movie_id = intent.getStringExtra("movie_id").toString()

//        Log.d("vote_average", vote_average)
//        Log.d("movie_title", movie_title)
//        Log.d("back_drop_path", back_drop_path)
//        Log.d("details", details)
//        Log.d("poster_path", poster_path)

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

    fun saveFavDB(view: View) {
        db = databaseBuilder(applicationContext, AppDb::class.java, "MovieDB").build()

        //Insert Case
        val thread = Thread {
            db.movieDao().getAllFavMovie().forEach()
            {
                Log.d("Fetch Records", "Id:  : ${it.movieId}")
                Log.d("Fetch Records", "Name:  : ${it.movieName}")
            }

            var movieEntity = MovieEntity()
            movieEntity.id = movie_id.toInt()
            movieEntity.movieId = movie_id.toInt()
            movieEntity.movieName = movie_title

            db.movieDao().saveMovie(movieEntity)
        }
        thread.start()
    }
}