package io.segu.sfilm.listing

import io.segu.sfilm.Api
import io.segu.sfilm.Movie
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by camlh on 7/11/2017.
 */

object MoviesListingParser {

    val RESULTS = "results"
    val OVERVIEW = "overview"
    val RELEASE_DATE = "release_date"
    val POSTER_PATH = "poster_path"
    val TITLE = "title"
    val VOTE_AVERAGE = "vote_average"

    private val BACKFDROP_PATH = "backdrop_path"
    private val ID = "id"

    fun parse(json: String): List<Movie> {
        val movies: MutableList<Movie> = ArrayList(24)

        val response: JSONObject = JSONObject(json)

        if (!response.isNull(RESULTS)) {

            val results: JSONArray = response.getJSONArray(RESULTS)

            for (i in 0 until results.length()) {
                movies.add(getMovie(results.getJSONObject(i)))
            }
        } else {
            // No results
        }

        return movies
    }

    @Throws(JSONException::class)
    fun getMovie(result: JSONObject) = Movie(
            result.getString(ID),
            result.getString(OVERVIEW),
            result.getString(RELEASE_DATE),
            Api.POSTER_PATH + result.getString(POSTER_PATH),
            Api.BACKDROP_PATH + result.getString(BACKFDROP_PATH),
            result.getString(TITLE),
            result.getDouble(VOTE_AVERAGE)
    )
}