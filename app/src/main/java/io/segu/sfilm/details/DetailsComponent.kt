package io.segu.sfilm.details

import dagger.Subcomponent

/**
 * Created by camlh on 7/15/2017.
 */

@DetailsScope
@Subcomponent(modules = arrayOf(DetailsModule::class))
interface DetailsComponent {
    fun inject(target: MovieDetailsFragment)
}