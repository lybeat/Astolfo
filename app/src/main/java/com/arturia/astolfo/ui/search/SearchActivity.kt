package com.arturia.astolfo.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.SearchView
import com.arturia.astolfo.data.greendao.DBManager
import com.arturia.astolfo.data.greendao.DaoHelper
import com.arturia.astolfo.data.greendao.SearchHistoryDao
import com.arturia.astolfo.data.model.SearchHistory
import com.arturia.astolfo.ui.base.SwipeActivity
import com.arturia.astolfo.widget.FlowLayoutManager
import kotlinx.android.synthetic.main.activity_search.*

/**
 * Author: Arturia
 * Date: 2017/11/16
 */
class SearchActivity : SwipeActivity(), SearchView.OnQueryTextListener {

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

        search_view.setOnQueryTextListener(this)
        adapter = HistoryAdapter(null)
        adapter.setOnItemClickListener { adapter, _, position ->
            val history: SearchHistory = adapter!!.data[position] as SearchHistory
            search_view.setQuery(history.name, true)
        }
        recycler_view.layoutManager = FlowLayoutManager()
        recycler_view.adapter = adapter

        tv_clear_history.setOnClickListener {
            DBManager<SearchHistory>().deleteAll(DaoHelper.get().getDaoSession().searchHistoryDao)
            adapter.setNewData(null)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.setNewData(DBManager<SearchHistory>().queryAll(DaoHelper.get().getDaoSession().searchHistoryDao,
                SearchHistoryDao.Properties.Time))
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null && query != "") {
            val history = SearchHistory(null, query, System.currentTimeMillis())
            DBManager<SearchHistory>().insert(DaoHelper.get().getDaoSession().searchHistoryDao, SearchHistoryDao.Properties.Name, history, query)
            SearchResultActivity.launch(this, query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean = true
}