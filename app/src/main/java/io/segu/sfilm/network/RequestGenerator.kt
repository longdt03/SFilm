package io.segu.sfilm.network

import okhttp3.Request

/**
 * Created by camlh on 7/11/2017.
 */

object RequestGenerator {

    private fun addDefaultHeaders(builder: Request.Builder) {
        builder.addHeader("Accept", "application/json")
    }

    operator fun get(url: String): Request {
        val builder: Request.Builder = Request.Builder().url(url)
        addDefaultHeaders(builder)
        return builder.build()
    }
}
