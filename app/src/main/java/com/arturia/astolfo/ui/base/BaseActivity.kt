package com.arturia.astolfo.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import rx.Subscription
import rx.subscriptions.CompositeSubscription

/**
 * Author: Arturia
 * Date: 2017/10/30
 */
abstract class BaseActivity : AppCompatActivity() {

    private var compositeSubscription: CompositeSubscription? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addSubscription(subscribeEvents())
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeSubscription?.clear()
    }

    private fun addSubscription(subscription: Subscription?) {
        if (subscription == null) return

        if (compositeSubscription == null) {
            compositeSubscription = CompositeSubscription()
        }
        compositeSubscription?.add(subscription)
    }

    open protected fun subscribeEvents(): Subscription? {
        return null
    }
}