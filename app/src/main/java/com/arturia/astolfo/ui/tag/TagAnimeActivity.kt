package com.arturia.astolfo.ui.tag

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.arturia.astolfo.R
import com.arturia.astolfo.data.model.Anime
import com.arturia.astolfo.ui.base.SwipeActivity
import com.arturia.astolfo.ui.browser.BrowserAdapter
import com.arturia.astolfo.ui.subject.SubjectActivity
import kotlinx.android.synthetic.main.activity_comment.*

/**
 * Author: Arturia
 * Date: 2017/11/16
 */
class TagAnimeActivity : SwipeActivity(), TagAnimeContract.View {

    private lateinit var presenter: TagAnimeContract.Presenter
    private lateinit var adapter: BrowserAdapter

    private var name = ""
    private var page = 1

    companion object {
        fun launch(context: Context, name: String) {
            val intent = Intent(context, TagAnimeActivity::class.java)
            intent.putExtra("name", name)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        name = intent.getStringExtra("name")

        toolbar.title = name
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { finish() }

        adapter = BrowserAdapter(this, null)
        adapter.setEnableLoadMore(true)
        adapter.setOnLoadMoreListener({
            page++
            presenter.loadAnimeList(name, page.toString())
        }, recycler_view)
        adapter.setOnItemClickListener { adapter, _, position ->
            val anime: Anime = adapter.data[position] as Anime
            SubjectActivity.launch(this, anime.href!!)
        }
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter

        TagAnimePresenter(this).loadAnimeList(name, "1")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

    override fun setPresenter(presenter: TagAnimeContract.Presenter) {
        this.presenter = presenter
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun handleError(error: Throwable) {
        error.printStackTrace()
    }

    override fun onAnimeListLoaded(animeList: List<Anime>) {
        if (page == 1) {
            adapter.setNewData(animeList)
        } else {
            adapter.loadMoreComplete()
            adapter.addData(animeList)
        }
    }
}