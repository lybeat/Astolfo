package com.arturia.astolfo.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arturia.astolfo.R
import com.arturia.astolfo.data.model.Calendar
import com.arturia.astolfo.ui.base.BaseFragment

/**
 * Author: Arturia
 * Date: 2017/11/2
 */
class CalendarFragment : BaseFragment() {

    companion object {
        fun newInstance(calendarList: ArrayList<Calendar>): CalendarFragment {
            val fragment = CalendarFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList("calendarList", calendarList)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getView(inflater: LayoutInflater?, container: ViewGroup?, attachToRoot: Boolean): View? {
        return inflater?.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun initData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}