package com.arturia.astolfo.ui.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.arturia.astolfo.R
import com.arturia.astolfo.ui.base.SwipeActivity
import com.arturia.astolfo.util.AppInfoUtil
import kotlinx.android.synthetic.main.activity_about.*

/**
 * Author: Arturia
 * Date: 2017/11/28
 */
class AboutActivity : SwipeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { finish() }
        tv_version.text = String.format("当前版本：V%s", AppInfoUtil.getVersionName(this))
        tv_domain.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse("http://arturia.cc/")
            startActivity(intent)
        }
    }
}