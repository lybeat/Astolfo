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

    @GET("/calendar")
    fun loadAnimeCalendar(): Observable<ResponseBody>

    @GET("/anime/tag")
    fun loadAnimeTag(): Observable<ResponseBody>

    @GET("/anime/browser")
    fun loadAnimeBrowser(@Query("sort") sort: String, @Query("page") page: String): Observable<ResponseBody>
}