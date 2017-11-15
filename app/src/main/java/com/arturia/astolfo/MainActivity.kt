package com.arturia.astolfo

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.util.Log
import android.view.MenuItem
import com.arturia.astolfo.ui.base.BaseActivity
import com.arturia.astolfo.ui.base.TabAdapter
import com.arturia.astolfo.ui.browser.BrowserFragment
import com.arturia.astolfo.ui.calendar.CalendarPagerFragment
import com.arturia.astolfo.ui.tag.TagFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

/**
 * Author: Arturia
 * Date: 2017/10/30
 */
class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation_view.setNavigationItemSelectedListener(this)

        val fragments = mutableListOf<Fragment>()
        fragments.add(CalendarPagerFragment())
        fragments.add(BrowserFragment())
        fragments.add(TagFragment())
        val titles = mutableListOf<String>()
        titles.add(getString(R.string.calendar))
        titles.add(getString(R.string.rank))
        titles.add(getString(R.string.anime_tag))
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_subscription -> Log.i("", "")
            R.id.nav_favorite -> Log.i("", "")
        }
        return true
    }
}