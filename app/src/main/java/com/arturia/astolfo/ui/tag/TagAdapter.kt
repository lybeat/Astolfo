package com.arturia.astolfo.ui.tag

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.arturia.astolfo.R
import com.arturia.astolfo.data.model.Tag
import com.arturia.astolfo.ui.search.SearchActivity
import com.moxun.tagcloudlib.view.TagsAdapter

/**
 * Author: Arturia
 * Date: 2017/11/16
 */
class TagAdapter(private var tags: List<Tag>) : TagsAdapter() {

//    private val colors = arrayOf(0xFFAA96B1, 0xFF768896, 0xFF78D8BD, 0xFFEFE19A, 0xFFEFADAF, 0xFF475264, 0xFFE85647)

    override fun getPopularity(position: Int): Int = position % 7

    override fun onThemeColorChanged(view: View?, themeColor: Int) {
    }

    override fun getView(context: Context?, position: Int, parent: ViewGroup?): View {
        val tv = TextView(context)
        tv.text = tags[position].name
        tv.gravity = Gravity.CENTER
        tv.setTextColor(context?.resources?.getColor(R.color.color_accent)!!)
        tv.setOnClickListener { SearchActivity.launch(context, tags[position].name) }
        return tv
    }

    override fun getItem(position: Int): Any = tags[position]

    override fun getCount(): Int = tags.size
}