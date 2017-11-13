package com.arturia.astolfo.ui.calendar

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arturia.astolfo.R
import com.arturia.astolfo.data.model.Calendar
import com.arturia.astolfo.ui.base.BaseFragment
import com.arturia.astolfo.ui.base.TabAdapter
import com.arturia.astolfo.util.DateUtil
import kotlinx.android.synthetic.main.fragment_pager_calendar.*
import java.util.*

/**
 * Author: Arturia
 * Date: 2017/10/31
 */
class CalendarPagerFragment : BaseFragment(), CalendarContract.View {

    private lateinit var presenter: CalendarContract.Presenter
    private var fragments = mutableListOf<Fragment>()
    private var titles = mutableListOf<String>()
    private var pagePosition = 0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_pager_calendar, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initTitles()
        CalendarPresenter(this).subscribe()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

    private fun initTitles() {
        titles.add("周日")
        titles.add("周一")
        titles.add("周二")
        titles.add("周三")
        titles.add("周四")
        titles.add("周五")
        titles.add("周六")
        val today = DateUtil.getWeekWithDate(Date())
        for (i in 0..6) {
            if (titles[i] == today) {
                titles[i] = "今天"
                pagePosition = i
            }
        }
    }

    override fun setPresenter(presenter: CalendarContract.Presenter) {
        this.presenter = presenter
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun handleError(error: Throwable) {
        error.printStackTrace()
        Log.e("CalendarPagerFragment", "@@@error: " + error.localizedMessage)
    }

    override fun onCalendarLoaded(calendar: List<Calendar>?) {
        initFragments(calendar)

        view_pager.adapter = TabAdapter(childFragmentManager, fragments, titles)
        tab_layout.setupWithViewPager(view_pager)
        tab_layout.tabMode = TabLayout.MODE_FIXED
        view_pager.currentItem = pagePosition
    }

    private fun initFragments(calendar: List<Calendar>?) {
        fragments.clear()
        val calendarList = ArrayList<Calendar>()
        calendarList += calendar!!
        fragments.add(CalendarFragment.newInstance(getCalendarList(calendar, "Sun")))
        fragments.add(CalendarFragment.newInstance(getCalendarList(calendar, "Mon")))
        fragments.add(CalendarFragment.newInstance(getCalendarList(calendar, "Tue")))
        fragments.add(CalendarFragment.newInstance(getCalendarList(calendar, "Wed")))
        fragments.add(CalendarFragment.newInstance(getCalendarList(calendar, "Thu")))
        fragments.add(CalendarFragment.newInstance(getCalendarList(calendar, "Fri")))
        fragments.add(CalendarFragment.newInstance(getCalendarList(calendar, "Sat")))
    }

    private fun getCalendarList(calendar: List<Calendar>?, week: String): ArrayList<Calendar> {
        val calendarList = ArrayList<Calendar>()
        calendar!!.filterTo(calendarList) { it.date == week }
        return calendarList
    }
}
