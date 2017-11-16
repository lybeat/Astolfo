package com.arturia.astolfo.data.source

import com.arturia.astolfo.Hosts
import com.arturia.astolfo.data.net.api.AnimeService
import com.arturia.astolfo.data.net.client.DefaultRetrofit
import okhttp3.ResponseBody
import rx.Observable

/**
 * Author: Arturia
 * Date: 2017/10/30
 */
class AnimeDataSource : AnimeContract {

    private var service: AnimeService = DefaultRetrofit()
            .create(Hosts.bangumi)
            .create(AnimeService::class.java)

    override fun loadAnimeCalendar(): Observable<ResponseBody> = service.loadAnimeCalendar()

    override fun loadAnimeTag(): Observable<ResponseBody> = service.loadAnimeTag()

    override fun loadAnimeBrowser(sort: String, page: String): Observable<ResponseBody> = service.loadAnimeBrowser(sort, page)

    override fun loadSubject(number: String): Observable<ResponseBody> = service.loadSubject("subject", number)

    override fun loadCharacter(number: String): Observable<ResponseBody> = service.loadCharacter("character", number)

    override fun loadComments(number: String, page: String): Observable<ResponseBody> = service.loadComments("subject", number, "comments", page)

    override fun loadTagAnime(name: String, page: String): Observable<ResponseBody> = service.loadTagAnime(name, page)

    override fun loadSearch(name: String, cat: String, page: String): Observable<ResponseBody> = service.loadSearch(name, cat, page)
}