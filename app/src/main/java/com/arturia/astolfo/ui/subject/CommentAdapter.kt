package com.arturia.astolfo.ui.subject

import android.content.Context
import android.widget.ImageView
import com.arturia.astolfo.R
import com.arturia.astolfo.data.model.Comment
import com.arturia.astolfo.util.UnitUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * Author: Arturia
 * Date: 2017/11/10
 */
class CommentAdapter(private var context: Context, comments: List<Comment>)
    : BaseQuickAdapter<Comment, BaseViewHolder>(R.layout.item_commment, comments) {

    private var options: RequestOptions = RequestOptions()

    init {
        options = options.placeholder(R.drawable.bg_placeholder).error(R.drawable.bg_placeholder)
                .transform(RoundedCorners(UnitUtil.dp2px(context, 18.0f)))
    }

    override fun convert(helper: BaseViewHolder?, item: Comment?) {
        val ivAvatar = helper?.getView<ImageView>(R.id.iv_avatar)
        Glide.with(context)
                .load("http:" + item?.user?.avatar)
                .apply(options)
                .into(ivAvatar)
        helper?.setText(R.id.tv_name, item?.user?.name)
        helper?.setText(R.id.tv_content, item?.content)
        helper?.setText(R.id.tv_time,item?.time)
    }
}