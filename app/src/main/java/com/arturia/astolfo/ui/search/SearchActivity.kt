package com.arturia.astolfo.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.SearchView
import com.arturia.astolfo.R
import com.arturia.astolfo.ui.base.SwipeActivity
import kotlinx.android.synthetic.main.activity_search.*

/**
 * Author: Arturia
 * Date: 2017/11/16
 */
class SearchActivity : SwipeActivity(), SearchView.OnQueryTextListener {

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
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null && query != "") {
            SearchResultActivity.launch(this, query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean = true
}