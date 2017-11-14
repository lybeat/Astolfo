package com.arturia.astolfo.ui.base

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.arturia.astolfo.R
import com.arturia.astolfo.widget.SwipeLayout

/**
 * Author: Arturia
 * Date: 2017/11/14
 */
abstract class SwipeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.SwipeTheme)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT

        val swipeLayout = SwipeLayout(this)
        swipeLayout.bindActivity(this)
    }
}