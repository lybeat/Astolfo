package com.arturia.astolfo.data.net.client.core

import com.arturia.astolfo.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

/**
 * Author: Arturia
 * Date: 2017/10/30
 */
abstract class BaseOkHttpClient {

    private val TIMEOUT_CONNECT = 30 // 30s
    private val TIMEOUT_READ = 10
    private val TIMEOUT_WRITE = 10

    fun create(): OkHttpClient {
        var builder = OkHttpClient.Builder()
        builder.connectTimeout(TIMEOUT_CONNECT.toLong(), TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_READ.toLong(), TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_WRITE.toLong(), TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE))
        builder = customize(builder)

        return builder.build()
    }

    abstract fun customize(builder: OkHttpClient.Builder): OkHttpClient.Builder
}