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

    fun loadAnimeBrowser(sort: String, page: String): Observable<ResponseBody>

    fun loadSubject(number: String): Observable<ResponseBody>

    fun loadCharacter(number: String): Observable<ResponseBody>

    fun loadComments(number: String, page: String): Observable<ResponseBody>

    fun loadTagAnime(name: String, page: String): Observable<ResponseBody>

    fun loadSearch(name: String, cat: String, page: String): Observable<ResponseBody>
}