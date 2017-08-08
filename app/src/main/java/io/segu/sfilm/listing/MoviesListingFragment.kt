package io.segu.sfilm.listing

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import butterknife.BindView
import io.segu.sfilm.BaseApplication
import io.segu.sfilm.Movie
import io.segu.sfilm.R
import io.segu.sfilm.base.BaseFragment
import io.segu.sfilm.listing.sorting.SortingDialogFragment
import javax.inject.Inject

/**
 * Created by camlh on 7/11/2017.
 */

class MoviesListingFragment : BaseFragment<Int>(), MoviesListingView {

    @Inject lateinit var presenter: MoviesListingPresenter

    @BindView(R.id.recycler_movies_listing)
    lateinit var recyclerView: RecyclerView

    private var adapter: RecyclerView.Adapter<*>? = null

    val movies: MutableList<Movie> = ArrayList(20)
    var callback: Callback? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        this.callback = context as Callback
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
        retainInstance = true

        (this.activity.application as BaseApplication).createListingComponent().inject(this)
    }

    override fun onLayout() = R.layout.fragment_movies_listing

    override fun bindModel(inflater: LayoutInflater?, layout: View) {
        this.initLayoutReferences()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_sort -> this.displaySortingOptions()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun showMovies(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        this.recyclerView.visibility = View.VISIBLE
        this.adapter!!.notifyDataSetChanged()
        this.callback!!.onMovieLoaded(movies.get(0))
    }

    override fun loadingStarted() {
        Snackbar.make(this.recyclerView, R.string.loading_movies, Snackbar.LENGTH_SHORT).show()
    }

    override fun loadingFailed(errorMessage: String) {
        Snackbar.make(this.recyclerView, errorMessage, Snackbar.LENGTH_INDEFINITE).show()
    }

    override fun onMovieClicked(movie: Movie) {
        this.callback!!.onMovieClicked(movie)
    }

    override fun onDetach() {
        this.callback = null
        super.onDetach()
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity.application as BaseApplication).releaseListingComponent()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.presenter.destroy()
    }

    private fun displaySortingOptions() {
        val sortingDialogFragment = SortingDialogFragment.Companion.newInstance(this.presenter)
        sortingDialogFragment.show(fragmentManager, "Select Quantity")
    }

    private fun initLayoutReferences() {
        this.recyclerView.setHasFixedSize(true)

        val columns: Int
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            columns = 2
        } else {
            columns = resources.getInteger(R.integer.no_of_columns)
        }

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this.activity, columns)

        this.recyclerView.layoutManager = layoutManager
        this.adapter = MoviesListingAdapter(this.movies, this)
        this.recyclerView.adapter = adapter
    }

    interface Callback {
        fun onMovieLoaded(movie: Movie)

        fun onMovieClicked(movie: Movie)
    }
}