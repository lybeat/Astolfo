package com.arturia.astolfo.ui.settings

import android.content.Intent
import android.os.Bundle
import com.arturia.astolfo.R
import com.arturia.astolfo.data.sp.SpSubscription
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
        about_astolfo.setOnClickListener { startActivity(Intent(this, AboutActivity::class.java)) }
        license.setOnClickListener { startActivity(Intent(this, LicenseActivity::class.java)) }
        switch_subscription.isChecked = SpSubscription.getSubscription(this)
        switch_subscription.setOnClickListener {
            if (SpSubscription.getSubscription(this)) {
                switch_subscription.isChecked = false
                SpSubscription.setSubscription(this,false)
            } else {
                switch_subscription.isChecked = true
                SpSubscription.setSubscription(this,true)
            }
        }
    }
}