package com.arturia.astolfo.ui.settings

import android.os.Bundle
import android.text.Html
import com.arturia.astolfo.R
import com.arturia.astolfo.ui.base.SwipeActivity
import kotlinx.android.synthetic.main.activity_license.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


/**
 * Author: Arturia
 * Date: 2017/11/28
 */
class LicenseActivity : SwipeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_license)

        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { finish() }
        tv_license.text = Html.fromHtml(readFromAssert("license.html"))
    }

    private fun readFromAssert(fileName: String): String {
        try {
            val isr = InputStreamReader(resources.assets.open(fileName))
            val buffer = BufferedReader(isr)
            var line: String?
            var result = ""
            line = buffer.readLine()
            while (line != null) {
                result += line
                line = buffer.readLine()
            }
            return result
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return ""
    }
}