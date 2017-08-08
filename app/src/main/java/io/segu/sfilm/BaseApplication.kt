package io.segu.sfilm

import android.app.Application
import android.os.StrictMode
import io.segu.sfilm.details.DetailsComponent
import io.segu.sfilm.details.DetailsModule
import io.segu.sfilm.favorites.FavoritesModule
import io.segu.sfilm.listing.ListingComponent
import io.segu.sfilm.listing.ListingModule
import io.segu.sfilm.network.NetworkModule

/**
 * Created by camlh on 7/11/2017.
 */

class BaseApplication : Application() {

    private var appComponent: AppComponent? = null
    private var detailsComponent: DetailsComponent? = null
    var listingComponent: ListingComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        StrictMode.enableDefaults()
        this.appComponent = createAppComponent()
    }

    fun createAppComponent(): AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule())
                .favoritesModule(FavoritesModule())
                .build()
    }

    fun createDetailsComponent(): DetailsComponent {
        this.detailsComponent = this.appComponent?.plus(DetailsModule())
        return this.detailsComponent!!
    }

    fun releaseDetailsComponent() {
        this.detailsComponent = null
    }

    fun createListingComponent(): ListingComponent {
        this.listingComponent = this.appComponent?.plus(ListingModule())
        return this.listingComponent!!
    }

    fun releaseListingComponent() {
        this.listingComponent = null
    }
}