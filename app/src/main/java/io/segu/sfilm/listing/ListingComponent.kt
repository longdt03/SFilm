package io.segu.sfilm.listing

import dagger.Subcomponent
import io.segu.sfilm.listing.sorting.SortingDialogFragment
import io.segu.sfilm.listing.sorting.SortingModule

/**
 * Created by camlh on 7/11/2017.
 */

@ListingScope
@Subcomponent(modules = arrayOf(ListingModule::class, SortingModule::class))
interface ListingComponent {
    fun inject(fragment: MoviesListingFragment): MoviesListingFragment

    fun inject(fragment: SortingDialogFragment): SortingDialogFragment
}