package com.arturia.astolfo

import android.app.Application
import io.realm.Realm

/**
 * Author: Arturia
 * Date: 2017/10/30
 */
class AstolfoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        application = this

        Realm.init(this)
    }

    companion object {

        lateinit var application: AstolfoApplication
    }
}