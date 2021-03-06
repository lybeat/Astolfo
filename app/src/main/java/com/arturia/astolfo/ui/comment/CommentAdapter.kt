package com.arturia.astolfo.ui.comment

import android.content.Context
import android.widget.ImageView
import android.widget.RatingBar
import com.arturia.astolfo.GlideApp
import com.arturia.astolfo.R
import com.arturia.astolfo.data.model.Comment
import com.arturia.astolfo.util.UnitUtil
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * Author: Arturia
 * Date: 2017/11/10
 */
class CommentAdapter(private var context: Context, comments: List<Comment>?)
    : BaseQuickAdapter<Comment, BaseViewHolder>(R.layout.item_comment, comments) {

    override fun convert(helper: BaseViewHolder?, item: Comment?) {
        val ivAvatar = helper?.getView<ImageView>(R.id.iv_avatar)
        val ratingBar = helper?.getView<RatingBar>(R.id.rating_bar)
        ratingBar?.rating = item?.star?.toFloat()!! / 2
        GlideApp.with(context)
                .load("http:" + item.user?.avatar)
                .transform(RoundedCorners(UnitUtil.dp2px(context, 18.0f)))
                .error(R.drawable.bg_placeholder)
                .into(ivAvatar)
        helper?.setText(R.id.tv_name, item.user?.name)
        helper?.setText(R.id.tv_content, item.content)
        helper?.setText(R.id.tv_time, item.time)
    }
}