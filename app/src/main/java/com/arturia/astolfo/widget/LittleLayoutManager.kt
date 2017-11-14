package com.arturia.astolfo.widget

import android.content.Context
import android.support.v7.widget.LinearLayoutManager

/**
 * Author: Arturia
 * Date: 2017/11/10
 */
class LittleLayoutManager(context: Context) : LinearLayoutManager(context) {

    override fun canScrollVertically(): Boolean = false

    override fun canScrollHorizontally(): Boolean = false
}