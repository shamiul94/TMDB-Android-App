package com.example.TMDB.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.TMDB.data.*
import io.reactivex.disposables.CompositeDisposable

class MoviesListViewModel : ViewModel() {

    private val networkService = NetworkService.getService()
    var moviesList: LiveData<PagedList<Movies>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 5
    private val moviesDataSourceFactory: MoviesDataSourceFactory =
        MoviesDataSourceFactory(compositeDisposable, networkService)

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        moviesList = LivePagedListBuilder(moviesDataSourceFactory, config).build()
    }


    fun getState(): LiveData<State> = Transformations.switchMap(
        moviesDataSourceFactory.moviesDataSourceLiveData,
        MoviesDataSource::state
    )

    fun retry() {
        moviesDataSourceFactory.moviesDataSourceLiveData.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return moviesList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}