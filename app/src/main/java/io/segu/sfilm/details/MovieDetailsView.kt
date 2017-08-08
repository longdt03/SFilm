package io.segu.sfilm.details

import io.segu.sfilm.Movie
import io.segu.sfilm.Review
import io.segu.sfilm.Video

/**
 * Created by camlh on 7/15/2017.
 */

interface MovieDetailsView {
    fun showDetails(movie: Movie)

    fun showTrailers(trailers: List<Video>)

    fun showReviews(reviews: List<Review>)

    fun showFavorited()

    fun showUnFavorited()
}