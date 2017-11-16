package com.arturia.astolfo.data.source

import okhttp3.ResponseBody
import rx.Observable

/**
 * Author: Arturia
 * Date: 2017/10/31
 */
class AnimeRepository : AnimeContract {

    private var dataSource: AnimeDataSource = AnimeDataSource()

    private object Holder {

        val instance = AnimeRepository()
    }

    companion object {

        fun get(): AnimeRepository = Holder.instance
    }

    override fun loadAnimeCalendar(): Observable<ResponseBody> = dataSource.loadAnimeCalendar()

    override fun loadAnimeTag(): Observable<ResponseBody> = dataSource.loadAnimeTag()

    override fun loadAnimeBrowser(sort: String, page: String): Observable<ResponseBody> = dataSource.loadAnimeBrowser(sort, page)

    override fun loadSubject(number: String): Observable<ResponseBody> = dataSource.loadSubject(number)

    override fun loadCharacter(number: String): Observable<ResponseBody> = dataSource.loadCharacter(number)

    override fun loadComments(number: String, page: String): Observable<ResponseBody> = dataSource.loadComments(number, page)

    override fun loadTagAnime(name: String, page: String) = dataSource.loadTagAnime(name, page)

    override fun loadSearch(name: String, cat: String, page: String): Observable<ResponseBody> = dataSource.loadSearch(name, cat, page)
}
