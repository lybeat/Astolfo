package com.arturia.astolfo.ui.tag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arturia.astolfo.R
import com.arturia.astolfo.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_pager_calendar.*

/**
 * Author: Arturia
 * Date: 2017/11/7
 */
class TagFragment : BaseFragment(), android.support.v7.widget.Toolbar.OnMenuItemClickListener {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.fragment_tag, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setNavigationIcon(R.drawable.ic_menu)
        toolbar.setNavigationOnClickListener {  }
        toolbar.setOnMenuItemClickListener(this)
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_search) {
            Toast.makeText(activity, "onMenuItemClick", Toast.LENGTH_SHORT).show()
        }

        return super.onOptionsItemSelected(item)
    }
}