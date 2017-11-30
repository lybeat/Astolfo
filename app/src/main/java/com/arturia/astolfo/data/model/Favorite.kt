package com.arturia.astolfo.data.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Author: Arturia
 * Date: 2017/11/30
 */
open class Favorite : RealmObject() {

    @PrimaryKey
    var href: String? = null
    var name: String? = null
    var cover: String? = null
    var info: String? = null
    var star: String? = null
    var time: Long? = null
}