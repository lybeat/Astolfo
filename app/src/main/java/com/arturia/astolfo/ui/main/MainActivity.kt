package com.arturia.astolfo.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.widget.Toast
import com.arturia.astolfo.ui.base.ImmersiveActivity
import com.arturia.astolfo.ui.base.TabAdapter
import com.arturia.astolfo.ui.browser.BrowserFragment
import com.arturia.astolfo.ui.calendar.CalendarPagerFragment
import com.arturia.astolfo.ui.tag.TagFragment
import com.arturia.astolfo.util.ColorUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

/**
 * Author: Arturia
 * Date: 2017/10/30
 */
class MainActivity : ImmersiveActivity(), FragmentListener {

    private var backTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.navigation_view, NavigationFragment()).commit()

        val fragments = mutableListOf<Fragment>()
        fragments.add(CalendarPagerFragment())
        fragments.add(BrowserFragment())
        fragments.add(TagFragment())
        val titles = mutableListOf<String>()
        titles.add(getString(R.string.calendar))
        titles.add(getString(R.string.rank))
        titles.add(getString(R.string.anime_tag))
        view_pager.adapter = TabAdapter(supportFragmentManager, fragments, titles)
        view_pager.offscreenPageLimit = 2

        layout_calendar.setOnClickListener {
            iv_calendar.setImageResource(R.drawable.ic_calendar_selected)
            tv_calendar.setTextColor(ColorUtil.getColor(this, R.color.color_accent))
            iv_browser.setImageResource(R.drawable.ic_tv_normal)
            tv_browser.setTextColor(ColorUtil.getColor(this, R.color.color_secondary2))
            iv_tag.setImageResource(R.drawable.ic_tag_normal)
            tv_tag.setTextColor(ColorUtil.getColor(this, R.color.color_secondary2))
            view_pager.setCurrentItem(0, false)
        }
        layout_browser.setOnClickListener {
            iv_calendar.setImageResource(R.drawable.ic_calendar_normal)
            tv_calendar.setTextColor(ColorUtil.getColor(this, R.color.color_secondary2))
            iv_browser.setImageResource(R.drawable.ic_tv_selected)
            tv_browser.setTextColor(ColorUtil.getColor(this, R.color.color_accent))
            iv_tag.setImageResource(R.drawable.ic_tag_normal)
            tv_tag.setTextColor(ColorUtil.getColor(this, R.color.color_secondary2))
            view_pager.setCurrentItem(1, false)
        }
        layout_tag.setOnClickListener {
            iv_calendar.setImageResource(R.drawable.ic_calendar_normal)
            tv_calendar.setTextColor(ColorUtil.getColor(this, R.color.color_secondary2))
            iv_browser.setImageResource(R.drawable.ic_tv_normal)
            tv_browser.setTextColor(ColorUtil.getColor(this, R.color.color_secondary2))
            iv_tag.setImageResource(R.drawable.ic_tag_selected)
            tv_tag.setTextColor(ColorUtil.getColor(this, R.color.color_accent))
            view_pager.setCurrentItem(2, false)
        }
    }

    override fun onNavigationClick() {
        drawer_layout.openDrawer(GravityCompat.START)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            if (System.currentTimeMillis() - backTime < 1500) {
                super.onBackPressed()
            } else {
                backTime = System.currentTimeMillis()
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show()
            }
        }
    }
}