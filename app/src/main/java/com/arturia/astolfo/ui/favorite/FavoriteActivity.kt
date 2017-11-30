package com.arturia.astolfo.ui.favorite

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.arturia.astolfo.R
import com.arturia.astolfo.data.model.Favorite
import com.arturia.astolfo.ui.base.SwipeActivity
import com.arturia.astolfo.ui.subject.SubjectActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_comment.*
import kotlin.properties.Delegates

/**
 * Author: Arturia
 * Date: 2017/11/30
 */
class FavoriteActivity : SwipeActivity() {

    private var realm: Realm by Delegates.notNull()
    private lateinit var adapter: FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        realm = Realm.getDefaultInstance()

        setSupportActionBar(toolbar)
        toolbar.title = getString(R.string.anime_favorite)
        toolbar.setNavigationOnClickListener { finish() }

        val result = realm.where(Favorite::class.java).findAll()

        adapter = FavoriteAdapter(this, result)
        adapter.setOnItemClickListener { adapter, _, position ->
            val favorite: Favorite = adapter.data[position] as Favorite
            SubjectActivity.launch(this, favorite.href!!)
        }
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}