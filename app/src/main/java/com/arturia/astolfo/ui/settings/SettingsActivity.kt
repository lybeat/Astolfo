package com.arturia.astolfo.ui.settings

import android.os.Bundle
import com.arturia.astolfo.R
import com.arturia.astolfo.ui.base.SwipeActivity
import kotlinx.android.synthetic.main.activity_settings.*

/**
 * Author: Arturia
 * Date: 2017/11/21
 */
class SettingsActivity : SwipeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        toolbar.setNavigationOnClickListener { finish() }
    }
}