package com.arturia.astolfo.ui.base

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Author: Arturia
 * Date: 2017/11/2
 */
class TabAdapter(fm: FragmentManager?,
                 private var fragment: List<Fragment>,
                 private var titles: List<String>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return fragment[position]
    }

    override fun getCount(): Int {
        return fragment.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }
}