package com.arturia.astolfo.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import rx.Subscription
import rx.subscriptions.CompositeSubscription

/**
 * Author: Arturia
 * Date: 2017/10/31
 */
abstract class BaseFragment : Fragment() {

    private var compositeSubscription: CompositeSubscription? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        addSubscription(subscribeEvents())

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
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