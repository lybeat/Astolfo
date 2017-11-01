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
            .create(Hosts.anime)
            .create(AnimeService::class.java)

    override fun loadAnimeCalendar(): Observable<ResponseBody> {
        return service.loadAnimeCalendar()
    }

    override fun loadAnimeTag(): Observable<ResponseBody> {
        return service.loadAnimeTag()
    }

    override fun loadAnimeBrowser(sort: String): Observable<ResponseBody> {
        return service.loadAnimeBrowser(sort)
    }
}