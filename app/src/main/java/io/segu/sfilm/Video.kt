package io.segu.sfilm

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by camlh on 7/11/2017.
 */

class Video : Parcelable {

    var id: String? = null
    var name: String? = null
    var site: String? = null
    var videoId: String? = null
    var size: Int = 0
    var type: String? = null

    constructor() {

    }

    protected constructor(source: Parcel) {
        this.id = source.readString()
        this.name = source.readString()
        this.site = source.readString()
        this.videoId = source.readString()
        this.size = source.readInt()
        this.type = source.readString()
    }

    override fun writeToParcel(parcel: Parcel?, i: Int) {
        parcel?.writeString(this.id)
        parcel?.writeString(this.name)
        parcel?.writeString(this.site)
        parcel?.writeString(this.videoId)
        parcel?.writeInt(this.size)
        parcel?.writeString(this.type)
    }

    override fun describeContents(): Int = 0

    companion object {
        val SITE_YOUTUBE = "YouTube"

        @Suppress("unused")
        @JvmField
        val CREATOR: Parcelable.Creator<Video> = object : Parcelable.Creator<Video> {
            override fun createFromParcel(parcel: Parcel): Video = Video(parcel)

            override fun newArray(size: Int): Array<Video?> = arrayOfNulls(size)
        }

        fun getThumbnailUrl(video: Video): String {
            if (SITE_YOUTUBE.equals(video.site, ignoreCase = true)) {
                return String.format(Api.YOUTUBE_THUMBNAIL_URL, video.id)
            } else {
                return Constants.EMPTY
            }
        }

        fun getUrl(video: Video): String {
            if (SITE_YOUTUBE.equals(video.site, ignoreCase = true)) {
                return String.format(Api.YOUTUBE_VIDEO_URL, video.id)
            } else {
                return Constants.EMPTY
            }
        }
    }
}