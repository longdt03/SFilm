package io.segu.sfilm

/**
 * Created by camlh on 7/11/2017.
 */

object Api {
    private val API_KEY = "bb541819d12727ddff5b3d8632db8572"

    val GET_POPULAR_MOVIES = "http://api.themoviedb.org/3/discover/movie?language=en&sort_by=popularity.desc&api_key=" + API_KEY
    val GET_HIGHEST_RATED_MOVIES = "http://api.themoviedb.org/3/discover/movie?vote_count.gte=500&language=en&sort_by=vote_average.desc&api_key=" + API_KEY
    val GET_TRAILERS = "http://api.themoviedb.org/3/movie/%s/videos?api_key=" + API_KEY
    val GET_REVIEWS = "http://api.themoviedb.org/3/movie/%s/reviews?api_key=" + API_KEY
    val POSTER_PATH = "http://image.tmdb.org/t/p/w342"
    val BACKDROP_PATH = "http://image.tmdb.org/t/p/w780"

    internal val YOUTUBE_VIDEO_URL = "http://www.youtube.com/watch?v=%1\$s"
    internal val YOUTUBE_THUMBNAIL_URL = "http://img.youtube.com/vi/%1\$s/0.jpg"
}