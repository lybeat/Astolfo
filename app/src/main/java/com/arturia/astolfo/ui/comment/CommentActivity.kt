package com.arturia.astolfo.ui.comment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.arturia.astolfo.R
import com.arturia.astolfo.data.model.Comment
import com.arturia.astolfo.ui.base.SwipeActivity
import kotlinx.android.synthetic.main.activity_comment.*

/**
 * Author: Arturia
 * Date: 2017/11/13
 */
class CommentActivity : SwipeActivity(), CommentContract.View {

    private lateinit var presenter: CommentContract.Presenter
    private lateinit var adapter: CommentAdapter

    private var page = 1
    private var number = ""

    companion object {
        fun launch(context: Context, href: String) {
            val intent = Intent(context, CommentActivity::class.java)
            intent.putExtra("number", href)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        toolbar.title = getString(R.string.comment_box)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener({ finish() })

        number = intent.getStringExtra("number")
        adapter = CommentAdapter(this, null)
        adapter.setEnableLoadMore(true)
        adapter.setOnLoadMoreListener({
            page++
            presenter.loadComment(number, page.toString())
        }, recycler_view)
        adapter.setOnItemClickListener { adapter, _, position ->
            val comment: Comment = adapter.data[position] as Comment
        }
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter

        CommentPresenter(this).loadComment(number, "1")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

    override fun setPresenter(presenter: CommentContract.Presenter) {
        this.presenter = presenter
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun handleError(error: Throwable) {
        error.printStackTrace()
    }

    override fun onCommentsLoaded(comments: List<Comment>) {
        if (page == 1) {
            adapter.setNewData(comments)
        } else {
            adapter.loadMoreComplete()
            adapter.addData(comments)
        }
    }
}