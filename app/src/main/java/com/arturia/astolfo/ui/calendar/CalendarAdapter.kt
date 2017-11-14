package com.arturia.astolfo.ui.calendar

import android.content.Context
import com.arturia.astolfo.GlideApp
import com.arturia.astolfo.R
import com.arturia.astolfo.data.model.Calendar
import com.arturia.astolfo.util.ScreenUtil
import com.arturia.astolfo.widget.FlowImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * Author: Arturia
 * Date: 2017/11/3
 */
class CalendarAdapter(private val context: Context, calendarList: List<Calendar>)
    : BaseQuickAdapter<Calendar, BaseViewHolder>(R.layout.item_calendar, calendarList) {

    private var screenWidth: Int = 0

    init {
        screenWidth = ScreenUtil.getScreenWidth(context)
    }

    override fun convert(helper: BaseViewHolder?, item: Calendar?) {
        helper?.setText(R.id.tv_name, item?.name)
        val ivCover = helper?.getView<FlowImageView>(R.id.iv_cover)
        ivCover?.setOriginalSize(screenWidth / 3, screenWidth / 3 * 360 / 270)
        GlideApp.with(context)
                .load("http:" + item?.cover)
                .override(screenWidth / 3, screenWidth / 3 * 360 / 270)
                .placeholder(R.drawable.bg_placeholder)
                .error(R.drawable.bg_placeholder)
                .into(ivCover)
    }
}