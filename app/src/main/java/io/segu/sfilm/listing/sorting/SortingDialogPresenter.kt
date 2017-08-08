package io.segu.sfilm.listing.sorting

/**
 * Created by camlh on 8/7/2017.
 */

interface SortingDialogPresenter {

    fun setLastSavedOption()

    fun onPopularMoviesSelected()

    fun onHighestRatedMoviesSelected()

    fun onFavoritesSelected()

    fun setView(view: SortingDialogView)

    fun destroy()
}
