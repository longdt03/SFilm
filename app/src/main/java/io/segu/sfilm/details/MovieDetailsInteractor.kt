package io.segu.sfilm.details

import io.segu.sfilm.Review
import io.segu.sfilm.Video
import rx.Observable

/**
 * Created by camlh on 7/11/2017.
 */

interface MovieDetailsInteractor {
    fun getTrailers(id: String): Observable<List<Video>>

    fun getReviews(id: String): Observable<List<Review>>
}