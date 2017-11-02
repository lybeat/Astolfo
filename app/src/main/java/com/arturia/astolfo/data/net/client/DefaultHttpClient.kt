package com.arturia.astolfo.data.net.client

import okhttp3.OkHttpClient

/**
 * Author: Arturia
 * Date: 2017/10/30
 */
class DefaultHttpClient : CacheHttpClient() {

    private fun getAcceptHeader(): String {
        return "application/json"
    }

    override fun customize(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        val newBuilder = super.customize(builder)

        builder.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                    .header("Accept", getAcceptHeader())
                    .header("User-Agent", "Miaopass")

            val request = requestBuilder.build()
            chain.proceed(request)
        }

        return super.customize(newBuilder)
    }
}