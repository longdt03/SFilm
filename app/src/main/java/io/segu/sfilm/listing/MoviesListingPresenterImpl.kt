package io.segu.sfilm.listing

import io.segu.sfilm.Movie
import io.segu.sfilm.util.RxUtils
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.schedulers.Schedulers

/**
 * Created by camlh on 8/8/2017.
 */

class MoviesListingPresenterImpl(private val interactor: MoviesListingInteractor) : MoviesListingPresenter {

    private var view: MoviesListingView? = null

    private var fetchSubscription: Subscription? = null

    override fun displayMovies() {
        this.showLoading()
        this.fetchSubscription = this.interactor.fetchMovies().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Action1<List<Movie>> { this.onMovieFetchSuccess(it) }, Action1<Throwable> { this.onMovieFetchFailed(it) })
    }

    override fun setView(view: MoviesListingView) {
        this.view = view
        this.displayMovies()
    }

    override fun destroy() {
        this.view = null
        RxUtils.unsubscribe(this.fetchSubscription!!)
    }

    private fun showLoading() {
        if (this.isViewAttached) {
            this.view!!.loadingStarted()
        }
    }

    private fun onMovieFetchSuccess(movies: List<Movie>) {
        if (this.isViewAttached) {
            this.view!!.showMovies(movies)
        }
    }

    private fun onMovieFetchFailed(e: Throwable) {
        this.view!!.loadingFailed(e.message!!)
    }

    private val isViewAttached: Boolean
        get() = view != null
}