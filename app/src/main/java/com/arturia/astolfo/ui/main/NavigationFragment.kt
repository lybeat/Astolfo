package com.arturia.astolfo.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arturia.astolfo.R
import com.arturia.astolfo.ui.base.BaseFragment
import com.arturia.astolfo.ui.favorite.FavoriteActivity
import com.arturia.astolfo.ui.settings.SettingsActivity
import kotlinx.android.synthetic.main.fragment_navigation.*

/**
 * Author: Arturia
 * Date: 2017/11/17
 */
class NavigationFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.fragment_navigation, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        layout_favorite.setOnClickListener { startActivity(Intent(activity, FavoriteActivity::class.java)) }
        layout_settings.setOnClickListener { startActivity(Intent(activity, SettingsActivity::class.java)) }
    }
}