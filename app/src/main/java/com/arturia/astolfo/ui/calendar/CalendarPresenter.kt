package com.arturia.astolfo.ui.calendar

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
class CalendarPresenter(private var view: CalendarContract.View) : CalendarContract.Presenter {

    private var repository: AnimeRepository = AnimeRepository.get()
    private var compositeSubscription: CompositeSubscription = CompositeSubscription()

    init {
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

        val week = arrayOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")

        val document: Document = Jsoup.parse(body?.string())
        val coverLists: Elements = document.getElementsByClass("coverList")
        for ((index, coverList) in coverLists.withIndex()) {
            val liList: Elements = coverList.getElementsByTag("li")
            for (li in liList) {
                val style = li.attr("style")
                val temp = style.split("'")
                var cover = temp[1]
                val a = li.getElementsByTag("a")
                val name: String = if (a.first().text().isEmpty()) a.last().text() else a.first().text()
                val href: String = a.first().attr("href")

                if (cover.contains("/c/")) {
                    cover = cover.replace("/c/", "/l/")
                }

                val calendar = Calendar(week[index], cover, name, href)
                calendars.add(calendar)
            }
        }

        return calendars
    }
}