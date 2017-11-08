package com.arturia.astolfo.ui.subject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.arturia.astolfo.R
import com.arturia.astolfo.data.model.Subject
import com.arturia.astolfo.ui.base.BaseActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_subject.*

/**
 * Author: Arturia
 * Date: 2017/11/7
 */
class SubjectActivity : BaseActivity(), SubjectContract.View {

    private lateinit var presenter: SubjectContract.Presenter

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

        val href = intent.getStringExtra("href")

        SubjectPresenter(this).loadSubject(href.split("/")[2])
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
        Glide.with(this)
                .load("http:" + subject.cover)
                .apply(RequestOptions().placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher))
                .into(iv_cover)
        tv_star.text = subject.star
        tv_appraisal.text = subject.appraisal
        tv_summary.text = subject.summary
        character_recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        character_recycler.adapter = CharacterAdapter(this, subject.characters)
        entry_recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        entry_recycler.adapter = EntryAdapter(this, subject.entries)
        like_recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        like_recycler.adapter = EntryAdapter(this, subject.likes)
    }
}