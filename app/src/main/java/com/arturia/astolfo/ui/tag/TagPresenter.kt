package com.arturia.astolfo.ui.tag

import android.util.Log
import com.arturia.astolfo.data.model.Tag
import com.arturia.astolfo.data.source.AnimeRepository
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

/**
 * Author: Arturia
 * Date: 2017/11/16
 */
class TagPresenter(private var view: TagContract.View) : TagContract.Presenter {

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

    override fun loadAnimeTag() {
        val subscription = repository.loadAnimeTag()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view.onTagLoaded(parseHtml(it)) }, { view.handleError(it) }, { view.hideLoading() })
        compositeSubscription.add(subscription)
    }

    private fun parseHtml(body: ResponseBody?): List<Tag> {
        val tags = mutableListOf<Tag>()

        val document: Document = Jsoup.parse(body?.string())
        val tagContainer = document.getElementById("tagList")
        val tagA = tagContainer.getElementsByTag("a")
        val tagSmall = tagContainer.getElementsByTag("small")
        for ((index, item) in tagA.withIndex()) {
            val name = item.text()
            val count = tagSmall[index].text()

            tags.add(Tag(name, count))
        }
        Log.i("TagPresenter", "@@@tags size: " + tags.size)
        return tags
    }
}