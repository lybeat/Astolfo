package com.arturia.astolfo.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.SearchView
import com.arturia.astolfo.R
import com.arturia.astolfo.data.model.SearchHistory
import com.arturia.astolfo.ui.base.SwipeActivity
import com.arturia.astolfo.widget.FlowLayoutManager
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_search.*
import kotlin.properties.Delegates

/**
 * Author: Arturia
 * Date: 2017/11/16
 */
class SearchActivity : SwipeActivity(), SearchView.OnQueryTextListener {

    private var realm: Realm by Delegates.notNull()

    private lateinit var adapter: HistoryAdapter

    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, SearchActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        realm = Realm.getDefaultInstance()

        search_view.setOnQueryTextListener(this)
        adapter = HistoryAdapter(null)
        adapter.setOnItemClickListener { adapter, _, position ->
            val history: SearchHistory = adapter!!.data[position] as SearchHistory
            search_view.setQuery(history.name, true)
        }
        recycler_view.layoutManager = FlowLayoutManager()
        recycler_view.adapter = adapter

        tv_clear_history.setOnClickListener {
            realm.executeTransaction { realm.deleteAll() }
            adapter.setNewData(null)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.setNewData(realm.where(SearchHistory::class.java).findAll())
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null && query != "") {
            val history = SearchHistory()
            history.name = query
            history.time = System.currentTimeMillis()
            realm.executeTransaction { realm.copyToRealmOrUpdate(history) }
            SearchResultActivity.launch(this, query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean = true
}