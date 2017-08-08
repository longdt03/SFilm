package io.segu.sfilm.details

import dagger.Provides
import io.segu.sfilm.favorites.FavoritesInteractor
import io.segu.sfilm.network.RequestHandler

/**
 * Created by camlh on 7/11/2017.
 */

class DetailsModule {

    @Provides
    @DetailsScope
    internal fun provideInteractor(requestHandler: RequestHandler): MovieDetailsInteractor {
        return MovieDetailsInteractorImpl(requestHandler)
    }

    @Provides
    @DetailsScope
    internal fun providePresenter(detailsInteractor: MovieDetailsInteractor, favoritesInteractor: FavoritesInteractor): MovieDetailsPresenter {
        return MovieDetailsPresenterImpl(detailsInteractor, favoritesInteractor)
    }
}