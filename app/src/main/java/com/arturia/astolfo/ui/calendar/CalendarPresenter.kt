package com.arturia.astolfo.ui.calendar

import com.arturia.astolfo.data.model.Calendar
import com.arturia.astolfo.data.source.AnimeRepository
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
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

    constructor(view: CalendarContract.View, compositeSubscription: CompositeSubscription) {
        this.view = view
        this.repository = AnimeRepository.instance
        this.compositeSubscription = compositeSubscription
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
                .subscribe({
                    t: ResponseBody? -> view.onCalendarLoaded(t)
                }, {
                    error -> view.handleError(error)
                }, {
                    view.hideLoading()
                })
        compositeSubscription.add(subscription)
    }

    private fun parseHtml(body: ResponseBody): List<Calendar> {
        var calendars: List<Calendar>

        val document: Document = Jsoup.parse(body.toString())
        val content: Element = document.getElementById("main")
        val coverLists: Elements = content.getElementsByClass("coverList")

        for (coverList in coverLists) {
            var calendar: Calendar
            val liList: Elements = coverList.getElementsByAttribute("li")
            for (li in liList) {

            }
        }
    }
}