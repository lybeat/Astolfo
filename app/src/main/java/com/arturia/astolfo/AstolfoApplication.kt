package com.arturia.astolfo

import android.app.Application

/**
 * Author: Arturia
 * Date: 2017/10/30
 */
class AstolfoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        application = this
    }

    companion object {

        lateinit var application: AstolfoApplication
    }
}