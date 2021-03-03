package com.example.TMDB.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.TMDB.data.State.DONE
import com.example.TMDB.data.State.ERROR
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class MoviesDataSource(
        private val networkService: NetworkService,
        private val compositeDisposable: CompositeDisposable)
    : PageKeyedDataSource<Int, Movies>() {

    var state: MutableLiveData<State> = MutableLiveData()
    private var retryCompletable: Completable? = null


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movies>) {
        updateState(State.LOADING)
        compositeDisposable.add(
                networkService.getNews(1)
                        .subscribe(
                                { response ->
                                    Log.d("Response asche:: 2 ::", "2")
                                    updateState(DONE)
                                    callback.onResult(response.results,
                                            null,
                                            2
                                    )
                                },
                                {
                                    updateState(ERROR)
                                    setRetry(Action { loadInitial(params, callback) })
                                }
                        )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movies>) {
        updateState(State.LOADING)
        compositeDisposable.add(
                networkService.getNews(params.key)
                        .subscribe(
                                { response ->
                                    updateState(DONE)
                                    callback.onResult(response.results,
                                            params.key + 1
                                    )
                                },
                                {
                                    updateState(ERROR)
                                    setRetry(Action { loadAfter(params, callback) })
                                }
                        )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movies>) {
    }

    private fun updateState(state: State) {
        this.state.postValue(state)
    }

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe())
        }
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }

}