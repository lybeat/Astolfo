package com.arturia.astolfo.ui.anime

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.arturia.astolfo.R
import com.arturia.astolfo.ui.base.BaseActivity

/**
 * Author: Arturia
 * Date: 2017/11/7
 */
class AnimeActivity : BaseActivity() {

    companion object {
        fun launch(context: Context, href: String) {
            val intent = Intent(context, AnimeActivity::class.java)
            intent.putExtra("href", href)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime)

        val href = intent.getStringExtra("href")
        Log.i("AnimeActivity", "@@@href: " + href)
    }
}