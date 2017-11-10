package com.arturia.astolfo.ui.subject

import android.content.Context
import android.widget.ImageView
import com.arturia.astolfo.R
import com.arturia.astolfo.data.model.Character
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * Author: Arturia
 * Date: 2017/11/8
 */
class CharacterAdapter(private var context: Context, characters: List<Character>)
    : BaseQuickAdapter<Character, BaseViewHolder>(R.layout.item_character, characters) {

    override fun convert(helper: BaseViewHolder?, item: Character?) {
        val ivAvatar = helper?.getView<ImageView>(R.id.iv_avatar)
        Glide.with(context)
                .load("http:" + item?.avatar)
                .apply(RequestOptions().placeholder(R.drawable.bg_placeholder).error(R.drawable.bg_placeholder))
                .into(ivAvatar)
        helper?.setText(R.id.tv_name, item?.name)
    }
}