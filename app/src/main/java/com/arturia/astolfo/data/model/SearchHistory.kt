package com.arturia.astolfo.data.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Author: Arturia
 * Date: 2017/11/30
 */
open class SearchHistory : RealmObject() {

    @PrimaryKey
    var name: String? = null
    var time: Long? = null
}