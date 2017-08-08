package io.segu.sfilm.details

import io.segu.sfilm.Movie

/**
 * Created by camlh on 7/15/2017.
 */
interface MovieDetailsPresenter {
    fun showDetails(movie: Movie)

    fun showTrailers(movie: Movie)

    fun showReviews(movie: Movie)

    fun showFavoriteButton(movie: Movie)

    fun onFavoriteClick(movie: Movie)

    fun setView(view: MovieDetailsView)

    fun destroy()
}