package com.arturia.astolfo.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arturia.astolfo.R
import com.arturia.astolfo.event.RxBus
import com.arturia.astolfo.event.SubscriptionEvent
import com.arturia.astolfo.ui.base.BaseFragment
import com.arturia.astolfo.ui.favorite.FavoriteActivity
import com.arturia.astolfo.ui.settings.SettingsActivity
import com.arturia.astolfo.ui.subscription.SubscriptionActivity
import kotlinx.android.synthetic.main.fragment_navigation.*
import rx.android.schedulers.AndroidSchedulers

/**
 * Author: Arturia
 * Date: 2017/11/17
 */
class NavigationFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.fragment_navigation, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        layout_subscription.setOnClickListener { startActivity(Intent(activity, SubscriptionActivity::class.java)) }
        layout_favorite.setOnClickListener { startActivity(Intent(activity, FavoriteActivity::class.java)) }
        layout_settings.setOnClickListener { startActivity(Intent(activity, SettingsActivity::class.java)) }
    }

    override fun subscribeEvents(): rx.Subscription? =
            RxBus.get().toObservable()
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext({
                        Log.i("NavigationFragment", "@@@subscribeEvents")
                        if (it is SubscriptionEvent) {
                            updateSubscription(it.count)
                        }
                    })
                    .subscribe(RxBus.defaultSubscriber())

    private fun updateSubscription(count: Int) {
        tv_update_count.text = count.toString()
    }
}