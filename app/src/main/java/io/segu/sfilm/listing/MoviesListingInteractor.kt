package io.segu.sfilm.listing

import io.segu.sfilm.Movie
import rx.Observable

/**
 * Created by camlh on 7/13/2017.
 */

interface MoviesListingInteractor {
    fun fetchMovies(): Observable<List<Movie>>
}