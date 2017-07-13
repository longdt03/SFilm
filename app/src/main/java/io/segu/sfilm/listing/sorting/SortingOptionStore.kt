package io.segu.sfilm.listing.sorting

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

/**
 * Created by camlh on 7/13/2017.
 */

class SortingOptionStore @Inject
constructor(context: Context) {
    private val pref: SharedPreferences

    init {
        this.pref = context.applicationContext.getSharedPreferences()
    }

    fun setSelectedOption(sortType: SortType) {
        val editor = this.pref.edit()
        editor.putInt(SELECTED_OPTION, sortType.value)
        editor.apply()
    }

    val selectedOption: Int
        get() = this.pref.getInt(SELECTED_OPTION, 0)

    companion object {
        private val SELECTED_OPTION = "selectedOption"
        private val PREF_NAME = "SortingOptionStore"
    }
}