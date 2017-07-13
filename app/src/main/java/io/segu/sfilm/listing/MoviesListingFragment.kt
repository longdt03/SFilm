package io.segu.sfilm.listing

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import io.segu.sfilm.BaseAplication
import io.segu.sfilm.Movie
import javax.inject.Inject

/**
 * Created by camlh on 7/11/2017.
 */

class MoviesListingFragment : Fragment(), MoviesListingView {

    @Inject lateinit var presenter: MoviesListingPresenter

    val movies: MutableList<Movie> = ArrayList(20)
    lateinit var callback: Callback

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        this.callback = context as Callback
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
        retainInstance = true

        (activity.application as BaseAplication).
    }

    override fun showMovies(movies: List<Movie>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadingStarted() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadingFailed(errorMessage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMovieClicked(movide: Movie) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    interface Callback {
        fun onMovieLoaded(movie: Movie)

        fun onMovieClicked(movie: Movie)
    }
}