package com.arturia.astolfo.ui.subject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.arturia.astolfo.R
import com.arturia.astolfo.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_subject_info.*

/**
 * Author: Arturia
 * Date: 2017/11/13
 */
class SubjectInfoActivity : BaseActivity() {

    companion object {
        fun launch(context: Context, name: String?, summary: String?, info: String?) {
            val intent = Intent(context, SubjectInfoActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("summary", summary)
            intent.putExtra("info", info)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subject_info)

        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { finish() }

        tv_name.text = intent.getStringExtra("name")
        tv_summary.text = intent.getStringExtra("summary")
        tv_info.text = intent.getStringExtra("info")
    }
}