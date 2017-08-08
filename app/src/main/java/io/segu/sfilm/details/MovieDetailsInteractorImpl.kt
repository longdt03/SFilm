package io.segu.sfilm.details

import io.segu.sfilm.Api
import io.segu.sfilm.Review
import io.segu.sfilm.Video
import io.segu.sfilm.network.RequestGenerator
import io.segu.sfilm.network.RequestHandler
import org.json.JSONException
import rx.Observable
import java.io.IOException

/**
 * Created by camlh on 7/11/2017.
 */

class MovieDetailsInteractorImpl(private val requestHandler: RequestHandler) : MovieDetailsInteractor {

    override fun getTrailers(id: String): Observable<List<Video>> {
        return Observable.fromCallable {
            this.getVideoList(id)
        }
    }

    override fun getReviews(id: String): Observable<List<Review>> {
        return Observable.fromCallable {
            this.getReviewList(id)
        }
    }

    @Throws(IOException::class, JSONException::class)
    fun getVideoList(id: String): List<Video> {
        val url = String.format(Api.GET_REVIEWS, id)

        val request = RequestGenerator.get(url)

        val body = this.requestHandler.request(request)
        return MovieDetailsParser.parseTrailers(body)
    }

    @Throws(IOException::class, JSONException::class)
    fun getReviewList(id: String) : List<Review> {
        val url = String.format(Api.GET_REVIEWS, id)
        val request = RequestGenerator.get(url)
        val body = this.requestHandler.request(request)
        return MovieDetailsParser.parseReviews(body)
    }
}