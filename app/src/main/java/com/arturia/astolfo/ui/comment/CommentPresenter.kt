package com.arturia.astolfo.ui.comment

import android.util.Log
import com.arturia.astolfo.data.model.Comment
import com.arturia.astolfo.data.model.User
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
 * Date: 2017/11/13
 */
class CommentPresenter(private var view: CommentContract.View) : CommentContract.Presenter {

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

    override fun loadComment(number: String, page: String) {
        val subscription = repository.loadComments(number, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view.onCommentsLoaded(parseHtml(it)) }, { view.handleError(it) }, { view.hideLoading() })
        compositeSubscription.add(subscription)
    }

    private fun parseHtml(body: ResponseBody?): List<Comment> {
        val document: Document = Jsoup.parse(body?.string())
        val commentContainer = document.getElementById("comment_box")
        val comments = mutableListOf<Comment>()
        if (commentContainer != null) {
            val commentDiv = commentContainer.getElementsByClass("item clearit")
            for (item in commentDiv) {
                var avatar = item.getElementsByTag("span")?.first()?.attr("style")
                val textDiv = item.getElementsByClass("text")
                val a = textDiv?.first()?.getElementsByTag("a")
                val href = a?.attr("href")
                val username = a?.first()?.text()
                val time = textDiv?.first()?.getElementsByTag("small")?.text()
                val star = textDiv?.first()?.getElementsByTag("span")
                val content = textDiv?.first()?.getElementsByTag("p")?.first()?.text()

                avatar = avatar?.split("'")?.get(1)?.replace("/s/", "/l/")

                Log.i("CommentPresenter", "@@@avatar: " + avatar)
                Log.i("CommentPresenter", "@@@href: " + href)
                Log.i("CommentPresenter", "@@@username" + username)
                Log.i("CommentPresenter", "@@@time: " + time)
                Log.i("CommentPresenter", "@@@star: " + star)
                Log.i("CommentPresenter", "@@@content: " + content)

                comments.add(Comment(content, formatTime(time), formatStar(star), User(username, avatar, href)))
            }
        }

        return comments
    }

    private fun formatStar(starSpan: Elements?): String {
        var star = "0"
        if (starSpan != null) {
            if (starSpan.size > 0) {
                star = starSpan.first().attr("class")
                star = if (star.isNotEmpty() && star.length > 8) {
                    star = star.split(" ")[0]
                    star.substring(6, star.length)
                } else {
                    "0"
                }
            } else {
                star = "0"
            }
        }
        return star
    }

    private fun formatTime(time: String?): String {
        var newTime = time?.split(" ")?.get(1)
        if (newTime == null || newTime == "") {
            return ""
        }
        when {
            newTime.endsWith("d") -> newTime = newTime.removeSuffix("d") + "天前"
            newTime.endsWith("h") -> newTime = newTime.removeSuffix("h") + "小时前"
            newTime.endsWith("m") -> newTime = newTime.removeSuffix("m") + "分钟前"
        }
        return newTime
    }
}