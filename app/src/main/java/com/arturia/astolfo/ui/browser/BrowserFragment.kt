package com.arturia.astolfo.ui.browser

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arturia.astolfo.R
import com.arturia.astolfo.data.model.Anime
import com.arturia.astolfo.ui.base.BaseFragment
import com.arturia.astolfo.ui.subject.SubjectActivity
import kotlinx.android.synthetic.main.fragment_calendar.*
import kotlinx.android.synthetic.main.fragment_pager_calendar.*

/**
 * Author: Arturia
 * Date: 2017/11/7
 */
class BrowserFragment : BaseFragment(), BrowserContract.View, Toolbar.OnMenuItemClickListener {

    private lateinit var presenter: BrowserContract.Presenter
    private lateinit var adapter: BrowserAdapter

    private var page = 1

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.fragment_browser, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setNavigationIcon(R.drawable.ic_menu)
        toolbar.setNavigationOnClickListener {  }
        toolbar.setOnMenuItemClickListener(this)

        adapter = BrowserAdapter(activity, null)
        adapter.setEnableLoadMore(true)
        adapter.setOnLoadMoreListener({
            page++
            presenter.loadAnimeBrowser("rank", page.toString())
        }, recycler_view)
        adapter.setOnItemClickListener { adapter, _, position ->
            val anime: Anime = adapter.data[position] as Anime
            SubjectActivity.launch(activity, anime.href!!)
        }
        recycler_view.layoutManager = LinearLayoutManager(activity)
        recycler_view.adapter = adapter

        BrowserPresenter(this).loadAnimeBrowser("rank", "1")
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_search) {
            Toast.makeText(activity, "onMenuItemClick", Toast.LENGTH_SHORT).show()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

    override fun setPresenter(presenter: BrowserContract.Presenter) {
        this.presenter = presenter
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun handleError(error: Throwable) {
        error.printStackTrace()
    }

    override fun onAnimeLoaded(animeList: List<Anime>) {
        if (page == 1) {
            adapter.setNewData(animeList)
        } else {
            adapter.loadMoreComplete()
            adapter.addData(animeList)
        }
    }
}