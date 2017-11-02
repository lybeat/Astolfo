package com.arturia.astolfo

import android.support.v4.app.Fragment
import com.arturia.astolfo.ui.base.BaseActivity
import com.arturia.astolfo.ui.base.TabAdapter
import com.arturia.astolfo.ui.calendar.CalendarPagerFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Author: Arturia
 * Date: 2017/10/30
 */
class MainActivity : BaseActivity() {

    override fun setContentView() {
        setContentView(R.layout.activity_main)
    }

    override fun initData() {

    }

    override fun initView() {
        val fragments = mutableListOf<Fragment>()
        fragments.add(CalendarPagerFragment())
//        fragments.add(CalendarPagerFragment())
        val titles = mutableListOf<String>()
        titles.add("日历")
//        titles.add("排行")
        view_pager.adapter = TabAdapter(supportFragmentManager, fragments, titles)
    }
}