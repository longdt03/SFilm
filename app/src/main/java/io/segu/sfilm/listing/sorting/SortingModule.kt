package io.segu.sfilm.listing.sorting

import dagger.Module
import dagger.Provides

/**
 * Created by camlh on 7/13/2017.
 */

@Module
class SortingModule {

    @Provides
    fun providesSortingDialogInteractor(store: SortingOptionStore): SortingDialogInteractor {
        return SortingDialogInteractorImpl(store)
    }

    @Provides
    fun providePresenter(interactor: SortingDialogInteractor): SortingDialogPresenter {
        return SortingDialogPresenterImpl(interactor)
    }
}