package com.arturia.astolfo.ui.browser

import android.content.Context
import android.widget.ImageView
import com.arturia.astolfo.R
import com.arturia.astolfo.data.model.Anime
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * Author: Arturia
 * Date: 2017/11/7
 */
class BrowserAdapter(private val context: Context, data: List<Anime>?)
    : BaseQuickAdapter<Anime, BaseViewHolder>(R.layout.item_anime, data) {

    override fun convert(helper: BaseViewHolder?, item: Anime?) {
        val ivCover = helper?.getView<ImageView>(R.id.iv_cover)
        Glide.with(context)
                .load("http:" + item?.cover)
                .apply(RequestOptions().placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher))
                .into(ivCover)
        helper?.setText(R.id.tv_name, item?.name)
        helper?.setText(R.id.tv_info, item?.info)
        helper?.setText(R.id.tv_tip, item?.tip)
    }
}