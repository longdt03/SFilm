package io.segu.sfilm.listing

import io.segu.sfilm.Api
import io.segu.sfilm.Movie
import io.segu.sfilm.favorites.FavoritesInteractor
import io.segu.sfilm.listing.sorting.SortType
import io.segu.sfilm.listing.sorting.SortingOptionStore
import io.segu.sfilm.network.RequestGenerator
import io.segu.sfilm.network.RequestHandler
import okhttp3.Request
import org.json.JSONException
import rx.Observable
import java.io.IOException

/**
 * Created by camlh on 8/8/2017.
 */

class MoviesListingInteractorImpl(private val favoritesInteractor: FavoritesInteractor,
                                  private val requestHandler: RequestHandler,
                                  private val sortingOptionStore: SortingOptionStore) : MoviesListingInteractor {


    override fun fetchMovies(): Observable<List<Movie>> {
        return Observable.fromCallable { this.getMovieList() }
    }

    @Throws(IOException::class, JSONException::class)
    private fun getMovieList(): List<Movie> {
        val selectedOption = this.sortingOptionStore.getSelectedOption

        if (selectedOption == SortType.MOST_POPULAR.value) {
            return this.fetchMovieList(Api.GET_POPULAR_MOVIES)
        } else if (selectedOption == SortType.HIGHEST_RATED.value) {
            return this.fetchMovieList(Api.GET_HIGHEST_RATED_MOVIES)
        } else {
            return this.favoritesInteractor.getFavorites()
        }
    }

    @Throws(IOException::class, JSONException::class)
    private fun fetchMovieList(url: String): List<Movie> {
        val request: Request = RequestGenerator.get(url)
        val response: String = this.requestHandler.request(request)
        return MoviesListingParser.parse(response)
    }
}