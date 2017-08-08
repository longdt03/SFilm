package io.segu.sfilm.listing

import io.segu.sfilm.Movie


/**
 * Created by camlh on 7/11/2017.
 */
interface MoviesListingView {
    fun showMovies(movies: List<Movie>)

    fun loadingStarted()

    fun loadingFailed(errorMessage: String)

    fun onMovieClicked(movie: Movie)
}