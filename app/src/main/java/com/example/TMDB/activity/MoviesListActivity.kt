package com.example.TMDB.activity

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.TMDB.R
import com.example.TMDB.adapter.MoviesListAdapter
import com.example.TMDB.data.Movies
import com.example.TMDB.data.State
import com.example.TMDB.data.State.ERROR
import com.example.TMDB.data.State.LOADING
import com.example.TMDB.interfaces.OnItemClickListener
import com.example.TMDB.viewModel.MoviesListViewModel
import kotlinx.android.synthetic.main.activity_news_list.*

class MoviesListActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var viewModel: MoviesListViewModel
    private lateinit var moviesListAdapter: MoviesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)


        viewModel = ViewModelProvider(this).get(MoviesListViewModel::class.java)

        initLayoutManager()
        initAdapter()
        initState()
    }

    private fun initLayoutManager() {
        recycler_view.layoutManager = GridLayoutManager(this, 2)
    }

    private fun initAdapter() {
        moviesListAdapter = MoviesListAdapter({ viewModel.retry() }, this)
        recycler_view.adapter = moviesListAdapter
        viewModel.moviesList.observe(this,
            Observer {
                moviesListAdapter.submitList(it)
            }
        )
    }

    private fun initState() {
        txt_error.setOnClickListener { viewModel.retry() }
        viewModel.getState().observe(this, Observer { state ->
            progress_bar.visibility = if (viewModel.listIsEmpty() && state == LOADING) VISIBLE else GONE
            txt_error.visibility = if (viewModel.listIsEmpty() && state == ERROR) VISIBLE else GONE
            if (!viewModel.listIsEmpty()) {
                moviesListAdapter.setState(state ?: State.DONE)
            }
        })
    }

    override fun onItemClicked(movies: Movies) {
        Toast.makeText(this, "User name ${movies.title} \n ", Toast.LENGTH_LONG)
            .show()
//        Log.i("USER_",user.username)

        val intent = Intent(this, MovieDetailsActivity::class.java)
        // To pass any data to next activity
        intent.putExtra("movie_title", movies.title)
        intent.putExtra("movie_id", movies.id.toString())
        intent.putExtra("back_drop_path", movies.backdropPath)
        intent.putExtra("poster_path", movies.posterPath)
        intent.putExtra("details", movies.overview)
        intent.putExtra("release_date", movies.releaseDate)
        intent.putExtra("vote_average", movies.voteAverage.toString())
        // start your next activity
        startActivity(intent)
    }

}