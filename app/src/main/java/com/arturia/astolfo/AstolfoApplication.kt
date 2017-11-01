package com.arturia.astolfo

import android.app.Application

/**
 * Author: Arturia
 * Date: 2017/10/30
 */
class AstolfoApplication : Application() {

    private var application: AstolfoApplication? = null

    override fun onCreate() {
        super.onCreate()

        application = this
    }

    companion object {

        fun getApplication(): AstolfoApplication? {
            return AstolfoApplication.getApplication()
        }
    }
}