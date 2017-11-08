package com.arturia.astolfo

import android.os.Bundle
import android.support.v4.app.Fragment
import com.arturia.astolfo.ui.base.BaseActivity
import com.arturia.astolfo.ui.base.TabAdapter
import com.arturia.astolfo.ui.browser.BrowserFragment
import com.arturia.astolfo.ui.calendar.CalendarPagerFragment
import com.arturia.astolfo.ui.tag.TagFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Author: Arturia
 * Date: 2017/10/30
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        setSupportActionBar(toolbar)

        val fragments = mutableListOf<Fragment>()
        fragments.add(CalendarPagerFragment())
        fragments.add(BrowserFragment())
        fragments.add(TagFragment())
        val titles = mutableListOf<String>()
        titles.add("每日放送")
        titles.add("排行榜")
        titles.add("动画标签")
        view_pager.adapter = TabAdapter(supportFragmentManager, fragments, titles)

        layout_calendar.setOnClickListener {
            view_pager.setCurrentItem(0, false)
        }
        layout_browser.setOnClickListener {
            view_pager.setCurrentItem(1, false)
        }
        layout_tag.setOnClickListener {
            view_pager.setCurrentItem(2, false)
        }
    }
}