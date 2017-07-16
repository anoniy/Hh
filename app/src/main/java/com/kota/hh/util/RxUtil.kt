package com.kota.hh.util

import rx.Subscription
import rx.subscriptions.CompositeSubscription

class RxUtil {

    companion object {
        fun unsubscribe(subscription: Subscription?) {
            if (subscription != null && !subscription.isUnsubscribed) {
                subscription.unsubscribe()
            }
        }
    }

}