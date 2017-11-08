package com.arturia.astolfo.ui.subject

import android.util.Log
import com.arturia.astolfo.data.model.*
import com.arturia.astolfo.data.source.AnimeRepository
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

/**
 * Author: Arturia
 * Date: 2017/11/8
 */
class SubjectPresenter(private var view: SubjectContract.View) : SubjectContract.Presenter {

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

    override fun loadSubject(number: String) {
        val subscription = repository.loadSubject(number)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view.onSubjectLoaded(parseHtml(it)) }, { view.handleError(it) }, { view.hideLoading() })
        compositeSubscription.add(subscription)
    }

    private fun parseHtml(body: ResponseBody?): Subject {
        val document: Document = Jsoup.parse(body?.string())
        val summary = document.getElementById("subject_summary").text()
        val infoBox = document.getElementsByClass("infobox")
        val coverA = infoBox.first().getElementsByTag("a")
        val cover = coverA.first().attr("href")
        val name = coverA.first().attr("title")
        val score = document.getElementsByClass("global_score")
        val span = score.first().getElementsByTag("span")
        val star = span.first().text()
        val appraisal = span.last().text()

        val characters = mutableListOf<Character>()
        val characterContainer = document.getElementsByClass("user")
        for (item in characterContainer) {
            val a = item.getElementsByTag("a")
            val href = a.first().attr("href")
            val cName = a.first().text()
            val userImage = item.getElementsByClass("userImage")
            var avatar = userImage.first().getElementsByTag("img").first().attr("src")
            val job = item.getElementsByClass("badge_job").text()
            val cv = item.getElementsByTag("a").last().text()

            Log.i("SubjectPresenter", "href: " + href)
            Log.i("SubjectPresenter", "cName: " + cName)
            Log.i("SubjectPresenter", "avatar: " + avatar)
            Log.i("SubjectPresenter", "job: " + job)
            Log.i("SubjectPresenter", "cv: " + cv)

            avatar = avatar.replace("/s/", "/m/")

            val character = Character(avatar, cName, href, job, cv)
            characters.add(character)
        }

        val entries = mutableListOf<Entry>()
        val entryContainer = document.getElementsByClass("browserCoverMedium clearit")
        val entryList = entryContainer.first().getElementsByTag("li")
        for (item in entryList) {
            val category = item.getElementsByTag("span").first().text()
            val a = item.getElementsByTag("a")
            val href = a.first().attr("href")
            var eCover = a.first().getElementsByTag("span").first().attr("style")
            val eName = a.last().text()

            eCover = eCover.split("'")[1]
            eCover = eCover.replace("/m/", "/l/")

            Log.i("SubjectPresenter", "category: " + category)
            Log.i("SubjectPresenter", "href: " + href)
            Log.i("SubjectPresenter", "eCover: " + eCover)
            Log.i("SubjectPresenter", "eName: " + eName)

            val entry = Entry(eCover, eName, href, category)
            entries.add(entry)
        }

        val likes = mutableListOf<Entry>()
        val likeContainer = document.getElementsByClass("coversSmall")
        val likeList = likeContainer.first().getElementsByTag("li")
        for (item in likeList) {
            val a = item.getElementsByTag("a")
            val href = a.first().attr("href")
            val lName = a.first().attr("title")
            var lCover = item.getElementsByTag("img").first().attr("src")

            Log.i("SubjectPresenter", "href: " + href)
            Log.i("SubjectPresenter", "lName: " + lName)
            Log.i("SubjectPresenter", "lCover: " + lCover)

            lCover = lCover.replace("/m/", "/l/")

            val like = Entry(lCover, lName, href, "")
            likes.add(like)
        }

        val comments = mutableListOf<Comment>()
        val commentContainer = document.getElementById("entry_list")
        val commentList = commentContainer.getElementsByClass("item clearit")
        for (item in commentList) {
            val a = item.getElementsByTag("a")
            val href = a.first().attr("href")
            val title = a.first().attr("title")
            val timeDiv = item.getElementsByClass("time")
            val timeA = timeDiv.first().getElementsByTag("a")
            val userHref = timeA.first().attr("href")
            val userName = timeA.first().text()
            val time = timeDiv.first().getElementsByTag("small").first().text()
            val content = item.getElementsByClass("content").first().text()

            Log.i("SubjectPresenter", "href: " + href)
            Log.i("SubjectPresenter", "title: " + title)
            Log.i("SubjectPresenter", "userHref: " + userHref)
            Log.i("SubjectPresenter", "userName: " + userName)
            Log.i("SubjectPresenter", "time: " + time)
            Log.i("SubjectPresenter", "content: " + content)

            val comment = Comment(href, title, content, time, User(userName, userHref))
            comments.add(comment)
        }

        Log.i("SubjectPresenter", "cover: " + cover)
        Log.i("SubjectPresenter", "name: " + name)
        Log.i("SubjectPresenter", "summary: " + summary)

        return Subject(cover, name, summary, characters, entries, likes, comments, star, appraisal, "")
    }
}