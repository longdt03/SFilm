package io.segu.sfilm.favorites

import io.segu.sfilm.Movie
import java.io.IOException

/**
 * Created by camlh on 7/14/2017.
 */

internal class FavoritesInteractorImpl(private val favoritesStore: FavoritesStore) : FavoritesInteractor {
    override fun isFavorite(id: String): Boolean {
        return this.favoritesStore.isFavorite(id)
    }

    override fun unFavorites(id: String) {
        this.favoritesStore.unfavorite(id)
    }

    override fun setFavorite(movie: Movie) {
        this.favoritesStore.setFavorite(movie)
    }

    override fun getFavorites(): List<Movie> {
        try {
            return this.favoritesStore.getFavorites()
        } catch (e: IOException) {
            e.printStackTrace()
            return ArrayList(0)
        }
    }
}