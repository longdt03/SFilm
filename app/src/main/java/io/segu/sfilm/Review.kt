package io.segu.sfilm

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by camlh on 7/11/2017.
 */

class Review : Parcelable {
    var id: String? = null
    var author: String? = null
    var content: String? = null
    var url: String? = null

    constructor() {}

    protected constructor(parcel: Parcel) {
        this.id = parcel.readString()
        this.author = parcel.readString()
        this.content = parcel.readString()
        this.url = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel?, i: Int) {
        parcel?.writeString(this.id)
        parcel?.writeString(this.author)
        parcel?.writeString(this.content)
        parcel?.writeString(this.url)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Review> {
        override fun createFromParcel(parcel: Parcel): Review {
            return Review(parcel)
        }

        override fun newArray(size: Int): Array<Review?> {
            return arrayOfNulls(size)
        }
    }
}