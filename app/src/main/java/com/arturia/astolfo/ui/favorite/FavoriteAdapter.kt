package com.arturia.astolfo.ui.favorite

import android.content.Context
import android.widget.ImageView
import com.arturia.astolfo.GlideApp
import com.arturia.astolfo.R
import com.arturia.astolfo.data.model.Favorite
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * Author: Arturia
 * Date: 2017/11/30
 */
class FavoriteAdapter(private var context: Context, favorites: List<Favorite>)
    : BaseQuickAdapter<Favorite, BaseViewHolder>(R.layout.item_anime, favorites) {

    override fun convert(helper: BaseViewHolder?, item: Favorite?) {
        helper?.setText(R.id.tv_name, item?.name)
        helper?.setText(R.id.tv_info, item?.info)
        helper?.setText(R.id.tv_tip, item?.star)
        val cover = helper?.getView<ImageView>(R.id.iv_cover)
        GlideApp.with(context)
                .load(item?.cover)
                .placeholder(R.drawable.bg_placeholder)
                .error(R.drawable.bg_placeholder)
                .into(cover)
    }
}