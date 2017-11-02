package com.arturia.astolfo.data.net.client

import com.arturia.astolfo.AstolfoApplication
import com.arturia.astolfo.data.net.client.core.BaseOkHttpClient
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File

/**
 * Author: Arturia
 * Date: 2017/10/30
 */
open class CacheHttpClient : BaseOkHttpClient() {

    private val cacheSize: Long = 1024 * 1024 * 10  // 10M

    override fun customize(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        val cacheFile = File(AstolfoApplication.application.cacheDir, "AstolfoCache")
        val cache = Cache(cacheFile, cacheSize)
        builder.cache(cache)

        return builder
    }
}