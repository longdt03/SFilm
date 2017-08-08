package io.segu.sfilm.listing.sorting

/**
 * Created by camlh on 7/14/2017.
 */

interface SortingDialogInteractor {
    fun getSelectedSortingOption(): Int

    fun setSortingOption(sortType: SortType)
}