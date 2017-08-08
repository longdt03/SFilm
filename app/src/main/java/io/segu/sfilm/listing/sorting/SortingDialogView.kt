package io.segu.sfilm.listing.sorting

/**
 * Created by camlh on 7/13/2017.
 */

interface SortingDialogView {
    fun setPopularChecked()

    fun setHighestRatedChecked()

    fun setFavoritesChecked()

    fun dismissDialog()
}