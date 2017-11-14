package com.arturia.astolfo.ui.calendar

import com.arturia.astolfo.data.model.Calendar
import com.arturia.astolfo.ui.base.mvp.BasePresenter
import com.arturia.astolfo.ui.base.mvp.BaseView

/**
 * Author: Arturia
 * Date: 2017/10/31
 */
interface CalendarContract {

    interface View : BaseView<Presenter> {

        fun showLoading()

        fun hideLoading()

        fun handleError(error: Throwable)

        fun onCalendarLoaded(calendar: List<Calendar>?)
    }

    interface Presenter : BasePresenter {

        fun loadAnimeCalendar()
    }
}