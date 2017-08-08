package io.segu.sfilm.details

import io.segu.sfilm.Movie
import io.segu.sfilm.Review
import io.segu.sfilm.Video
import io.segu.sfilm.favorites.FavoritesInteractor
import io.segu.sfilm.util.RxUtils
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by camlh on 7/15/2017.
 */

class MovieDetailsPresenterImpl(private val movieDetailsInteractor: MovieDetailsInteractor, private val favoritesInteractor: FavoritesInteractor) : MovieDetailsPresenter {
    private var view: MovieDetailsView? = null
    private var trailersSubscription: Subscription? = null
    private var reviewSubscription: Subscription? = null

    private val isViewAttached: Boolean
        get() = this.view != null

    override fun showDetails(movie: Movie) {
        if (this.isViewAttached) {
            this.view!!.showDetails(movie)
        }
    }

    override fun showTrailers(movie: Movie) {
        this.trailersSubscription = this.movieDetailsInteractor.getTrailers(movie.id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ this.onGetTrailersSuccess(it) })
    }

    override fun showReviews(movie: Movie) {
        this.reviewSubscription = this.movieDetailsInteractor.getReviews(movie.id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ this.onGetReviewsSuccess(it) })
    }

    override fun showFavoriteButton(movie: Movie) {
        val isFavorite = this.favoritesInteractor.isFavorite(movie.id)
        if (this.isViewAttached) {
            if (isFavorite) {
                this.view?.showFavorited()
            } else {
                this.view?.showUnFavorited()
            }
        }
    }

    override fun onFavoriteClick(movie: Movie) {
        if (this.isViewAttached) {
            val isFavorite = this.favoritesInteractor.isFavorite(movie.id)

            if (isFavorite) {
                this.favoritesInteractor.unFavorites(movie.id)
                this.view?.showUnFavorited()
            } else {
                this.favoritesInteractor.setFavorite(movie)
                this.view?.showFavorited()
            }
        }
    }

    override fun setView(view: MovieDetailsView) {
        this.view = view
    }

    override fun destroy() {
        this.view = null
        RxUtils.unsubscribe(this.trailersSubscription!!, this.reviewSubscription!!)
    }

    fun onGetTrailersSuccess(videos: List<Video>) {
        if (this.isViewAttached) {
            this.view?.showTrailers(videos)
        }
    }

    fun onGetReviewsSuccess(reviews: List<Review>) {
        if (this.isViewAttached) {
            this.view?.showReviews(reviews)
        }
    }
}
