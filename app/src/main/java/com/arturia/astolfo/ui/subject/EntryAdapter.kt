package com.arturia.astolfo.ui.subject

import android.content.Context
import android.widget.ImageView
import com.arturia.astolfo.R
import com.arturia.astolfo.data.model.Entry
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * Author: Arturia
 * Date: 2017/11/8
 */
class EntryAdapter(private var context: Context, entries: List<Entry>)
    : BaseQuickAdapter<Entry, BaseViewHolder>(R.layout.item_entry, entries) {

    override fun convert(helper: BaseViewHolder?, item: Entry?) {
        val ivAvatar = helper?.getView<ImageView>(R.id.iv_cover)
        Glide.with(context)
                .load("http:" + item?.cover)
                .apply(RequestOptions().placeholder(R.drawable.bg_placeholder).error(R.drawable.bg_placeholder))
                .into(ivAvatar)
        helper?.setText(R.id.tv_name, item?.name)
    }
}