package com.arturia.astolfo.ui.calendar

import android.util.Log
import com.arturia.astolfo.data.model.Calendar
import com.arturia.astolfo.data.source.AnimeRepository
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

/**
 * Author: Arturia
 * Date: 2017/10/31
 */
class CalendarPresenter : CalendarContract.Presenter {

    private var view: CalendarContract.View
    private var repository: AnimeRepository
    private var compositeSubscription: CompositeSubscription

    constructor(view: CalendarContract.View) {
        this.view = view
        this.repository = AnimeRepository.get()
        this.compositeSubscription = CompositeSubscription()
        this.view.setPresenter(this)
    }

    override fun subscribe() {
        loadAnimeCalendar()
    }

    override fun unsubscribe() {
        compositeSubscription.clear()
    }

    override fun loadAnimeCalendar() {
        val subscription = repository.loadAnimeCalendar()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view.onCalendarLoaded(parseHtml(it)) }, { view.handleError(it) })
        compositeSubscription.add(subscription)
    }

    private fun parseHtml(body: ResponseBody?): List<Calendar> {
        val calendars = mutableListOf<Calendar>()

        val document: Document = Jsoup.parse(body?.string())
        val coverLists: Elements = document.getElementsByClass("coverList")

        Log.i("CalendarPresenter", "@@@coverList size: " + coverLists.size)

        for (coverList in coverLists) {
            val liList: Elements = coverList.getElementsByTag("li")
            for (li in liList) {
                val a = li.getElementsByTag("a")
                val name: String = a.text()
                val href: String = a.attr("href")
                Log.i("CalendarPresenter", "@@@name: " + name)
                Log.i("CalendarPresenter", "@@@href: " + href)
                val calendar = Calendar("Sun", "", name, href)
                calendars.add(calendar)
            }
        }

        return calendars
    }
}