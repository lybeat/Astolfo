package com.arturia.astolfo.ui.character

import android.util.Log
import com.arturia.astolfo.data.model.Character
import com.arturia.astolfo.data.source.AnimeRepository
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

/**
 * Author: Arturia
 * Date: 2017/11/13
 */
class CharacterPresenter(private var view: CharacterContract.View) : CharacterContract.Presenter {

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

    override fun loadCharacter(number: String) {
        val subscription = repository.loadCharacter(number)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view.onCharacterLoaded(parseHtml(it)) }, { view.handleError(it) }, { view.hideLoading() })
        compositeSubscription.add(subscription)
    }

    private fun parseHtml(body: ResponseBody?): Character {
        val document: Document = Jsoup.parse(body?.string())
        val name = document.getElementsByClass("nameSingle")?.first()?.getElementsByTag("a")?.first()?.text()
        val infobox = document.getElementsByClass("infobox")
        val avatar = infobox?.first()?.getElementsByTag("a")?.first()?.getElementsByTag("img")?.attr("src")
        var info = ""
        val infoContainer = infobox?.first()?.getElementById("infobox")
        val infoLi = infoContainer?.getElementsByTag("li")
        if (infoLi != null && infoLi.size > 0) {
            for (item in infoLi) {
                info += item.text()
                info += "\n"
            }
        }
        val summary = document.getElementsByClass("detail")?.first()?.text()

        Log.i("CharacterPresenter", "@@@avatar: " + avatar)
        Log.i("CharacterPresenter", "@@@name: " + name)
        Log.i("CharacterPresenter", "@@@info: " + info)
        Log.i("CharacterPresenter", "@@@summary: " + summary)

        return Character(avatar, name, "", info, summary)
    }
}