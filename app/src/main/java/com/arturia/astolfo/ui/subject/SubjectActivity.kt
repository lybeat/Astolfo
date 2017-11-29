package com.arturia.astolfo.ui.subject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arturia.astolfo.GlideApp
import com.arturia.astolfo.R
import com.arturia.astolfo.data.model.Character
import com.arturia.astolfo.data.model.Entry
import com.arturia.astolfo.data.model.Subject
import com.arturia.astolfo.ui.base.SwipeActivity
import com.arturia.astolfo.ui.character.CharacterActivity
import com.arturia.astolfo.ui.comment.CommentActivity
import com.arturia.astolfo.ui.comment.CommentAdapter
import com.arturia.astolfo.util.BlurTransformation
import com.arturia.astolfo.widget.LittleLayoutManager
import com.arturia.astolfo.widget.ObservableScrollView
import kotlinx.android.synthetic.main.activity_subject.*



/**
 * Author: Arturia
 * Date: 2017/11/7
 */
class SubjectActivity : SwipeActivity(), SubjectContract.View, ObservableScrollView.ScrollViewListener {

    private lateinit var presenter: SubjectContract.Presenter
    private var number = ""

    companion object {
        fun launch(context: Context, href: String) {
            val intent = Intent(context, SubjectActivity::class.java)
            intent.putExtra("href", href)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subject)

        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener({ finish() })
        toolbar.setBackgroundColor(resources.getColor(R.color.color_accent))
        toolbar.background.alpha = 0

        val href = intent.getStringExtra("href")
        val temp = href.split("/")
        if (temp.size > 2) {
            number = temp[2]
            SubjectPresenter(this).loadSubject(number)
        }

        scroll_view.setOnScrollChangedListener(this)

        tv_favorite.setOnClickListener {  }
        tv_subscription.setOnClickListener {  }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

    override fun setPresenter(presenter: SubjectContract.Presenter) {
        this.presenter = presenter
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun handleError(error: Throwable) {
    }

    override fun onSubjectLoaded(subject: Subject) {
        toolbar.title = subject.name
        header_layout.visibility = View.VISIBLE
        GlideApp.with(this)
                .load("http://" + subject.cover)
                .into(iv_cover)
        GlideApp.with(this)
                .load("http://" + subject.cover)
                .transform(BlurTransformation(this))
                .into(iv_header)
        tv_star.text = subject.star
        if (subject.star != null && subject.star != "") {
            rating_bar.rating = subject.star!!.toFloat() / 2
            rating_bar.visibility = View.VISIBLE
        }
        tv_appraisal.text = subject.appraisal
        if (subject.info != null && subject.info != "") {
            tv_info.text = subject.info
        }
        if (subject.summary != null && subject.summary != "") {
            tv_summary.text = subject.summary
            tv_summary.visibility = View.VISIBLE
            layout_more_info.visibility = View.VISIBLE
            layout_action.visibility = View.VISIBLE
            tv_more_info.setOnClickListener {
                if (subject.info != null && subject.info != "") {
                    SubjectInfoActivity.launch(this, subject.name, subject.summary, subject.info)
                }
            }
        }
        if (subject.characters!!.isNotEmpty()) {
            recycler_character.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            val characterAdapter = CharacterAdapter(this, subject.characters!!)
            characterAdapter.setOnItemClickListener { adapter, _, position ->
                val character: Character = adapter.data[position] as Character
                CharacterActivity.launch(this, character.href)
            }
            recycler_character.adapter = characterAdapter
            layout_character.visibility = View.VISIBLE
        }
        if (subject.entries!!.isNotEmpty()) {
            recycler_entry.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            val entryAdapter = EntryAdapter(this, subject.entries!!)
            entryAdapter.setOnItemClickListener { adapter, _, position ->
                val entry: Entry = adapter.data[position] as Entry
                SubjectActivity.launch(this, entry.href!!)
            }
            recycler_entry.adapter = entryAdapter
            layout_entry.visibility = View.VISIBLE
        }
        if (subject.likes!!.isNotEmpty()) {
            recycler_like.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            val likeAdapter = EntryAdapter(this, subject.likes!!)
            likeAdapter.setOnItemClickListener { adapter, _, position ->
                val like: Entry = adapter.data[position] as Entry
                SubjectActivity.launch(this, like.href!!)
            }
            recycler_like.adapter = likeAdapter
            layout_like.visibility = View.VISIBLE
        }
        if (subject.comments!!.isNotEmpty()) {
            recycler_comment.layoutManager = LittleLayoutManager(this)
            recycler_comment.adapter = CommentAdapter(this, subject.comments!!)
            layout_comment.visibility = View.VISIBLE
            tv_more_comment.setOnClickListener { CommentActivity.launch(this, number) }
        }
    }

    override fun onScrollChanged(view: ObservableScrollView, x: Int, y: Int, oldX: Int, oldY: Int) {
        when {
            y <= 0 -> toolbar.background?.alpha = 0
            y in 1..400 -> {
                val percent = y.toFloat() / 400
                toolbar.background?.alpha = (255 * percent).toInt()
            }
            y > 400 -> toolbar.background?.alpha = 255
        }
    }
}