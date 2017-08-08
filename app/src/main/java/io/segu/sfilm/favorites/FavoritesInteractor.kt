package io.segu.sfilm.favorites

import io.segu.sfilm.Movie

/**
 * Created by camlh on 7/14/2017.
 */

interface FavoritesInteractor {

    fun setFavorite(movie: Movie)

    fun isFavorite(id: String) : Boolean

    fun getFavorites() : List<Movie>

    fun unFavorites(id: String)
}