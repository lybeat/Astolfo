package com.arturia.astolfo.data.net.client.core

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Author: Arturia
 * Date: 2017/10/30
 */
abstract class BaseRetrofit {

    fun create(host: String): Retrofit {
        val builder = Retrofit.Builder()
        builder.baseUrl(host)
                .client(getHttpClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
        return builder.build()
    }

    abstract fun getHttpClient(): OkHttpClient
}