package com.arturia.astolfo.ui.browser

import com.arturia.astolfo.data.model.Anime
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
 * Date: 2017/11/7
 */
class BrowserPresenter(private var view: BrowserContract.View) : BrowserContract.Presenter {

    private var repository: AnimeRepository = AnimeRepository.get()
    private var compositeSubscription: CompositeSubscription = CompositeSubscription()

    init {
        this.view.setPresenter(this)
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
        compositeSubscription.clear()
    }

    override fun loadAnimeBrowser(sort: String, page: String) {
        val subscription = repository.loadAnimeBrowser(sort, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view.onAnimeLoaded(parseHtml(it)) }, { view.handleError(it) }, { view.hideLoading() })

    }

    private fun parseHtml(body: ResponseBody?): List<Anime> {
        val animeList = mutableListOf<Anime>()

        val document: Document = Jsoup.parse(body?.string())
        val ul: Element = document.getElementById("browserItemList")
        val liList: Elements = ul.getElementsByTag("li")
        for (li in liList) {
            val div = li.getElementsByTag("div")
            val href = li.getElementsByTag("a").attr("href")
            var cover = li.getElementsByTag("img").attr("src")
            val name = div.first().getElementsByTag("a").text()
            val info = div.first().getElementsByClass("info tip").text()
            val rateInfo = div.first().getElementsByClass("rateInfo")
            val star = rateInfo.first().getElementsByClass("fade").first().text()
            val tip = rateInfo.first().getElementsByClass("tip_j").first().text()

            if (cover.contains("/s/")) {
                cover = cover.replace("/s/", "/c/")
            }

            val anime = Anime(cover, name, href, info, star, tip)
            animeList.add(anime)
        }

        return animeList
    }
}