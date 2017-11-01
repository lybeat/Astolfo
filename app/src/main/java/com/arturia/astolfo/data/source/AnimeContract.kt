package com.arturia.astolfo.data.source

import okhttp3.ResponseBody
import rx.Observable

/**
 * Author: Arturia
 * Date: 2017/10/30
 */
interface AnimeContract {

    fun loadAnimeCalendar(): Observable<ResponseBody>

    fun loadAnimeTag(): Observable<ResponseBody>

    fun loadAnimeBrowser(sort: String): Observable<ResponseBody>
}