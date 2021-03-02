package com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.R
import com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.adapter.NewsListAdapter
import com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.data.News
import com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.data.State
import com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.data.State.ERROR
import com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.data.State.LOADING
import com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.interfaces.OnItemClickListener
import com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.viewModel.NewsListViewModel
import kotlinx.android.synthetic.main.activity_news_list.*

class NewsListActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var viewModel: NewsListViewModel
    private lateinit var newsListAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)


        viewModel = ViewModelProvider(this).get(NewsListViewModel::class.java)

        initLayoutManager()
        initAdapter()
        initState()
    }

    private fun initLayoutManager() {
        recycler_view.layoutManager = GridLayoutManager(this, 2)
    }

    private fun initAdapter() {
        newsListAdapter = NewsListAdapter({ viewModel.retry() }, this)
        recycler_view.adapter = newsListAdapter
        viewModel.newsList.observe(this,
            Observer {
                newsListAdapter.submitList(it)
            }
        )
    }

    private fun initState() {
        txt_error.setOnClickListener { viewModel.retry() }
        viewModel.getState().observe(this, Observer { state ->
            progress_bar.visibility = if (viewModel.listIsEmpty() && state == LOADING) VISIBLE else GONE
            txt_error.visibility = if (viewModel.listIsEmpty() && state == ERROR) VISIBLE else GONE
            if (!viewModel.listIsEmpty()) {
                newsListAdapter.setState(state ?: State.DONE)
            }
        })
    }

    override fun onItemClicked(news: News) {
        Toast.makeText(this, "User name ${news.title} \n ", Toast.LENGTH_LONG)
            .show()
//        Log.i("USER_",user.username)

        val intent = Intent(this, MovieDetailsActivity::class.java)
        // To pass any data to next activity
//        intent.putExtra("keyIdentifier", value)
        // start your next activity
        startActivity(intent)
    }

}