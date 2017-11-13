package com.arturia.astolfo.ui.calendar

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arturia.astolfo.R
import com.arturia.astolfo.data.model.Calendar
import com.arturia.astolfo.ui.base.BaseFragment
import com.arturia.astolfo.ui.subject.SubjectActivity
import kotlinx.android.synthetic.main.fragment_calendar.*

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

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recycler_view.layoutManager = GridLayoutManager(activity, 3)
        val calendarAdapter = CalendarAdapter(activity, arguments.getParcelableArrayList("calendarList"))
        calendarAdapter.setOnItemClickListener { adapter, _, position ->
            val calendar: Calendar = adapter.data[position] as Calendar
            SubjectActivity.launch(activity, calendar.href)
        }
        recycler_view.adapter = calendarAdapter
    }
}