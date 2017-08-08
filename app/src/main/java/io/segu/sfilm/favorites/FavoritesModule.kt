package io.segu.sfilm.favorites

import dagger.Module
import dagger.Provides
import io.segu.sfilm.AppModule
import javax.inject.Singleton

/**
 * Created by camlh on 7/14/2017.
 */

@Module(includes = arrayOf(AppModule::class))
class FavoritesModule {
    @Provides
    @Singleton
    internal fun provideFavoritesInteractor(store: FavoritesStore): FavoritesInteractor {
        return FavoritesInteractorImpl(store)
    }
}