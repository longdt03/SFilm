package io.segu.sfilm.listing.sorting

/**
 * Created by camlh on 7/14/2017.
 */

class SortingDialogInteractorImpl constructor(private val sortingOptionStore: SortingOptionStore) : SortingDialogInteractor {

    override fun getSelectedSortingOption(): Int {
        return this.sortingOptionStore.getSelectedOption
    }

    override fun setSortingOption(sortType: SortType) {
        this.sortingOptionStore.setSelectedOption(sortType)
    }
}