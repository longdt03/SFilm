package io.segu.sfilm.network

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

/**
 * Created by camlh on 7/13/2017.
 */

class RequestHandler(private val okHttpClient: OkHttpClient) {

    @Throws(IOException::class)
    fun request(request: Request): String {
        val response = this.okHttpClient.newCall(request).execute()

        val body = response.body()!!.string()

        if (response.isSuccessful) {
            return body
        } else {
            throw RuntimeException(response.message())
        }
    }
}