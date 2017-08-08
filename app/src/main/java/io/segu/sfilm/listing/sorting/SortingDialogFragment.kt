package io.segu.sfilm.listing.sorting

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.widget.RadioButton
import android.widget.RadioGroup
import butterknife.BindView
import butterknife.ButterKnife
import io.segu.sfilm.BaseApplication
import io.segu.sfilm.R
import io.segu.sfilm.listing.MoviesListingPresenter
import javax.inject.Inject

/**
 * Created by camlh on 8/7/2017.
 */

class SortingDialogFragment : DialogFragment(), SortingDialogView, RadioGroup.OnCheckedChangeListener {

    @Inject
    internal val sortingDialogPresenter: SortingDialogPresenter? = null

    @BindView(R.id.most_popular)
    lateinit var mostPopular: RadioButton

    @BindView(R.id.highest_rated)
    lateinit var highestRated: RadioButton

    @BindView(R.id.favorites)
    lateinit var favorites: RadioButton

    @BindView(R.id.sorting_group)
    lateinit var sortingOptionsGroup: RadioGroup

    companion object {
        private var moviesListingPresenter: MoviesListingPresenter? = null

        fun newInstance(moviesListingPresenter: MoviesListingPresenter): SortingDialogFragment {
            this.moviesListingPresenter = moviesListingPresenter
            return SortingDialogFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        (activity.application as BaseApplication).listingComponent!!.inject(this)
        sortingDialogPresenter!!.setView(this)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView = inflater.inflate(R.layout.sorting_options, null)
        ButterKnife.bind(this, dialogView)
        this.initViews()

        val dialog = Dialog(activity)
        dialog.setContentView(dialogView)
        dialog.setTitle(R.string.sort_by)
        dialog.show()

        return dialog
    }

    private fun initViews() {
        this.sortingDialogPresenter!!.setLastSavedOption()
        this.sortingOptionsGroup.setOnCheckedChangeListener(this)
    }

    override fun onCheckedChanged(radioGroup: RadioGroup?, checkId: Int) {
        when (checkId) {
            R.id.most_popular -> {
                this.sortingDialogPresenter!!.onPopularMoviesSelected()
                moviesListingPresenter!!.displayMovies()
            }

            R.id.highest_rated -> {
                this.sortingDialogPresenter!!.onHighestRatedMoviesSelected()
                moviesListingPresenter!!.displayMovies()
            }

            R.id.favorites -> {
                this.sortingDialogPresenter!!.onFavoritesSelected()
                moviesListingPresenter!!.displayMovies()
            }
        }
    }

    override fun setPopularChecked() {
        this.mostPopular.isChecked = true
    }

    override fun setHighestRatedChecked() {
        this.highestRated.isChecked = true
    }

    override fun setFavoritesChecked() {
        this.favorites.isChecked = true
    }

    override fun dismissDialog() {
        this.dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.sortingDialogPresenter!!.destroy()
    }
}