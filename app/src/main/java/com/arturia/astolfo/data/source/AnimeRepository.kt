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
        fun get(): AnimeRepository {
            return Holder.instance
        }
    }

    override fun loadAnimeCalendar(): Observable<ResponseBody> {
        return dataSource.loadAnimeCalendar()
    }

    override fun loadAnimeTag(): Observable<ResponseBody> {
        return dataSource.loadAnimeTag()
    }

    override fun loadAnimeBrowser(sort: String): Observable<ResponseBody> {
        return dataSource.loadAnimeBrowser(sort)
    }
}