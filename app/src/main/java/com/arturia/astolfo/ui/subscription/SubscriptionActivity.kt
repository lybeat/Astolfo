package com.arturia.astolfo.ui.subscription

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.arturia.astolfo.R
import com.arturia.astolfo.data.model.Subscription
import com.arturia.astolfo.ui.base.SwipeActivity
import com.arturia.astolfo.ui.subject.SubjectActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_common.*
import kotlin.properties.Delegates

/**
 * Author: Arturia
 * Date: 2017/12/1
 */
class SubscriptionActivity : SwipeActivity() {

    private var realm: Realm by Delegates.notNull()
    private lateinit var adapter: SubscriptionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)

        realm = Realm.getDefaultInstance()

        setSupportActionBar(toolbar)
        toolbar.title = getString(R.string.anime_subscription)
        toolbar.setNavigationOnClickListener { finish() }

        val result = realm.where(Subscription::class.java).findAll()
        adapter = SubscriptionAdapter(this, result)
        adapter.setOnItemClickListener { adapter, _, position ->
            val subscription = adapter.data[position] as Subscription
            SubjectActivity.launch(this, subscription.href!!)
        }
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}