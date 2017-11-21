package com.arturia.astolfo.data.greendao

import com.arturia.astolfo.AstolfoApplication

/**
 * Author: Arturia
 * Date: 2017/11/21
 */
class DaoHelper {

    private val dbName = "astolfo-db"

    private var daoMaster: DaoMaster
    private var daoSession: DaoSession

    init {
        val helper: DaoMaster.DevOpenHelper = DaoMaster.DevOpenHelper(AstolfoApplication.application, dbName)
        daoMaster = DaoMaster(helper.writableDatabase)
        daoSession = daoMaster.newSession()
    }

    private object Holder {
        val instance = DaoHelper()
    }

    companion object {
        fun get(): DaoHelper = Holder.instance
    }

    fun getDaoMaster(): DaoMaster = daoMaster

    fun getDaoSession(): DaoSession = daoSession
}