package com.arturia.astolfo.ui.calendar

import android.content.Context
import com.arturia.astolfo.R
import com.arturia.astolfo.data.model.Calendar
import com.arturia.astolfo.util.ScreenUtil
import com.arturia.astolfo.widget.FlowImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * Author: Arturia
 * Date: 2017/11/3
 */
class CalendarAdapter(private val context: Context, calendarList: List<Calendar>)
    : BaseQuickAdapter<Calendar, BaseViewHolder>(R.layout.item_calendar, calendarList) {

    private var screenWidth: Int = 0
    private var options: RequestOptions = RequestOptions()

    init {
        screenWidth = ScreenUtil.getScreenWidth(context)
        options = options.placeholder(R.drawable.bg_placeholder).error(R.drawable.bg_placeholder)
    }

    override fun convert(helper: BaseViewHolder?, item: Calendar?) {
        helper?.setText(R.id.tv_name, item?.name)
        val ivCover = helper?.getView<FlowImageView>(R.id.iv_cover)
        ivCover?.setOriginalSize(screenWidth / 3, screenWidth / 3 * 360 / 270)
        Glide.with(context)
                .load("http:" + item?.cover)
                .apply(options.override(screenWidth / 3, screenWidth / 3 * 360 / 270))
                .into(ivCover)
    }
}