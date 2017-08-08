package io.segu.sfilm.favorites

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import io.segu.sfilm.Movie
import java.io.IOException
import javax.inject.Singleton

/**
 * Created by camlh on 7/14/2017.
 */


class FavoritesStore @Singleton
constructor(context: Context) {
    private val pref: SharedPreferences

    init {
        this.pref = context.applicationContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    companion object {
        private val PREF_NAME = "FavoritesStore"
    }

    fun setFavorite(movie: Movie) {
        val editor = this.pref.edit()
        val moshi = Moshi.Builder().build()

        val jsonAdapter: JsonAdapter<Movie> = moshi.adapter(Movie::class.java)
        val movieJson: String = jsonAdapter.toJson(movie)
        editor.putString(movie.id, movieJson)
        editor.apply()
    }

    fun isFavorite(id: String): Boolean {
        val movieJson = this.pref.getString(id, null)
        if (!TextUtils.isEmpty(movieJson)) {
            return true
        }
        return false
    }

    @Throws(IOException::class)
    fun getFavorites(): List<Movie> {
        val allEntries = this.pref.all
        val movies = ArrayList<Movie>(24)
        val moshi = Moshi.Builder().build()

        for ((key) in allEntries) {
            val movieJson = this.pref.getString(key, null)

            if (!TextUtils.isEmpty(movieJson)) {
                val jsonAdapter = moshi.adapter(Movie::class.java)

                val movie = jsonAdapter.fromJson(movieJson)
                movies.add(movie)
            } else {
                // Do nothing
            }
        }
        return movies
    }

    fun unfavorite(id: String) {
        val editor = this.pref.edit()
        editor.remove(id)
        editor.apply()
    }
}
