package io.segu.sfilm

import dagger.Component
import io.segu.sfilm.details.DetailsComponent
import io.segu.sfilm.details.DetailsModule
import io.segu.sfilm.favorites.FavoritesModule
import io.segu.sfilm.listing.ListingComponent
import io.segu.sfilm.listing.ListingModule
import io.segu.sfilm.network.NetworkModule
import javax.inject.Singleton

/**
 * Created by camlh on 7/11/2017.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class, FavoritesModule::class))
interface AppComponent {
    fun plus(detailsModule: DetailsModule): DetailsComponent

    fun plus(listingModule: ListingModule): ListingComponent
}