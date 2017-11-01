package com.arturia.astolfo.data.net.client

import com.arturia.astolfo.data.net.client.core.BaseRetrofit
import okhttp3.OkHttpClient

/**
 * Author: Arturia
 * Date: 2017/10/30
 */
class DefaultRetrofit: BaseRetrofit() {

    private var httpClient: DefaultHttpClient = DefaultHttpClient()

    override fun getHttpClient(): OkHttpClient {
        return httpClient.create()
    }
}