package io.segu.sfilm.listing

import dagger.Module
import dagger.Provides
import io.segu.sfilm.favorites.FavoritesInteractor
import io.segu.sfilm.listing.sorting.SortingOptionStore
import io.segu.sfilm.network.RequestHandler

/**
 * Created by camlh on 7/13/2017.
 */

@Module
class ListingModule {

    @Provides
    fun provideMovieListingInteractor(favoritesInteractor: FavoritesInteractor,
                                      requestHandler: RequestHandler,
                                      sortingOptionStore: SortingOptionStore): MoviesListingInteractor {
        return MoviesListingInteractorImpl(favoritesInteractor, requestHandler, sortingOptionStore)
    }

    @Provides
    fun provideMovieListingPresenter(interactor: MoviesListingInteractor): MoviesListingPresenter {
        return MoviesListingPresenterImpl(interactor)
    }
}