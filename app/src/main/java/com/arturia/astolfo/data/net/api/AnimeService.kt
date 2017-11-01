package com.arturia.astolfo.data.net.api

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Author: Arturia
 * Date: 2017/10/30
 */
interface AnimeService {

    @GET()
    fun loadAnimeCalendar(): Observable<ResponseBody>

    @GET()
    fun loadAnimeTag(): Observable<ResponseBody>

    @GET()
    fun loadAnimeBrowser( @Query("sort") sort: String): Observable<ResponseBody>
}