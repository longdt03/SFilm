package io.segu.sfilm

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by camlh on 7/11/2017.
 */

class Movie(val id: String,
            val overview: String,
            val realeaseDate: String,
            val posterPath: String,
            val backdropPath: String,
            val title: String,
            val voteAverage: Double) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel?, i: Int) {
        parcel?.writeString(this.id)
        parcel?.writeString(this.overview)
        parcel?.writeString(this.realeaseDate)
        parcel?.writeString(this.posterPath)
        parcel?.writeString(this.backdropPath)
        parcel?.writeString(this.title)
        parcel?.writeDouble(this.voteAverage)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie = Movie(parcel)

        override fun newArray(size: Int): Array<Movie?> = arrayOfNulls(size)
    }
}