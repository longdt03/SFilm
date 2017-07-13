package io.segu.sfilm.util

import rx.Subscription

/**
 * Created by camlh on 7/11/2017.
 */

class RxUtils {
    fun unsubscribe(sub: Subscription) {
        if (!sub.isUnsubscribed) {
            sub.unsubscribe()
        }
    }

    fun unsubscribe(sub: Array<Subscription>) {
        sub.forEach {
            if (!it.isUnsubscribed) {
                it.unsubscribe()
            }
        }
    }
}