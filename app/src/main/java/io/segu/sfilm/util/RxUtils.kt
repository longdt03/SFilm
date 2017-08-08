package io.segu.sfilm.util

import rx.Subscription

/**
 * Created by camlh on 7/11/2017.
 */

object RxUtils {
    fun unsubscribe(sub: Subscription?) {
        if (!sub!!.isUnsubscribed) {
            sub.unsubscribe()
        }
    }

    fun unsubscribe(vararg subscriptions: Subscription?) {
        for (subscription in subscriptions) {
            if (!subscription!!.isUnsubscribed) {
                subscription.unsubscribe()
            } else {
                // Already unsubscribed
            }
        }
    }
}