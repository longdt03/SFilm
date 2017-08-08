package io.segu.sfilm.listing.sorting

/**
 * Created by camlh on 8/7/2017.
 */

class SortingDialogPresenterImpl(private val interactor: SortingDialogInteractor) : SortingDialogPresenter {

    private var view: SortingDialogView? = null

    override fun setLastSavedOption() {
        if (this.isViewAttached()) {
            val selectedOption = this.interactor.getSelectedSortingOption()

            if (selectedOption == SortType.MOST_POPULAR.value) {
                this.view!!.setPopularChecked()
            } else if (selectedOption == SortType.HIGHEST_RATED.value) {
                this.view!!.setHighestRatedChecked()
            } else {
                this.view!!.setFavoritesChecked()
            }
        }
    }

    override fun onPopularMoviesSelected() {
        if (this.isViewAttached()) {
            this.interactor.setSortingOption(SortType.MOST_POPULAR)
            this.view!!.dismissDialog()
        }
    }

    override fun onHighestRatedMoviesSelected() {
        if (this.isViewAttached()) {
            this.interactor.setSortingOption(SortType.HIGHEST_RATED)
            this.view!!.dismissDialog()
        }
    }

    override fun onFavoritesSelected() {
        if (this.isViewAttached()) {
            this.interactor.setSortingOption(SortType.RAVORITES)
            this.view!!.dismissDialog()
        }
    }

    override fun setView(view: SortingDialogView) {
        this.view = view
    }

    override fun destroy() {
        this.view = null
    }

    private fun isViewAttached() = this.view != null
}
