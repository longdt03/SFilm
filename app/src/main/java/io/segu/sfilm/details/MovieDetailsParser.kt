package io.segu.sfilm.details

import io.segu.sfilm.Review
import io.segu.sfilm.Video
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by camlh on 7/13/2017.
 */

object MovieDetailsParser {

    val RESULTS = "results"
    val ID = "id"
    val NAME = "name"
    val SITE = "site"
    val KEY = "key"
    val SIZE = "size"
    val TYPE = "type"
    val AUTHOR = "author"
    val CONTENT = "content"
    val URL = "url"

    @Throws(JSONException::class)
    fun parseTrailers(body: String): List<Video> {
        val trailers = ArrayList<Video>(4)
        val response = JSONObject(body)

        if (!response.isNull(RESULTS)) {
            val results = response.getJSONArray(RESULTS)

            for (i in 0 until results.length()) {
                val video = Video()
                val videoJson = results.getJSONObject(i)

                if (!videoJson.isNull(ID)) {
                    video.id = videoJson.getString(ID)
                }

                if (!videoJson.isNull(NAME)) {
                    video.name = videoJson.getString(NAME)
                }

                if (!videoJson.isNull(SITE)) {
                    video.site = videoJson.getString(SITE)
                }

                if (!videoJson.isNull(KEY)) {
                    video.videoId = videoJson.getString(KEY)
                }

                if (!videoJson.isNull(SIZE)) {
                    video.size = videoJson.getInt(SIZE)
                }

                if (!videoJson.isNull(TYPE)) {
                    video.type = videoJson.getString(TYPE)
                }

                trailers.add(video)
            }
        }
        return trailers
    }

    @Throws(JSONException::class)
    fun parseReviews(body: String): List<Review> {

        val reviews = ArrayList<Review>(4)
        val response = JSONObject(body)

        if (!response.isNull(RESULTS)) {
            val results = response.getJSONArray(RESULTS)

            for (i in 0 until results.length()) {
                val review = Review()
                val reviewJson = results.getJSONObject(i)

                if (!reviewJson.isNull(ID)) {
                    review.id = reviewJson.getString(ID)
                }

                if (!reviewJson.isNull(AUTHOR)) {
                    review.author = reviewJson.getString(AUTHOR)
                }

                if (!reviewJson.isNull(CONTENT)) {
                    review.content = reviewJson.getString(CONTENT)
                }

                if (!reviewJson.isNull(URL)) {
                    review.url = reviewJson.getString(URL)
                }

                reviews.add(review)
            }
        }
        return reviews
    }
}
