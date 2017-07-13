package io.segu.sfilm.listing

/**
 * Created by camlh on 7/11/2017.
 */

interface MoviesListingPresenter {

    fun displayMovies()

    fun setView(view : MoviesListingView)

    fun destroy()
}