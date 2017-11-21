package com.arturia.astolfo.data.greendao

import org.greenrobot.greendao.AbstractDao
import org.greenrobot.greendao.Property

/**
 * Author: Arturia
 * Date: 2017/11/21
 */
class DBManager<T> {

    fun insert(dao: AbstractDao<T, Long>, property: Property, t: T, name: String) {
        val temp = queryByProperty(dao, property, name)
        if (temp != null) {
            dao.delete(temp)
        }
        dao.insert(t)
    }

    fun delete(dao: AbstractDao<T, Long>, t: T) {
        dao.delete(t)
    }

    fun deleteAll(dao: AbstractDao<T, Long>) {
        dao.deleteAll()
    }

    fun update(dao: AbstractDao<T, Long>, t: T) {
        dao.update(t)
    }

    fun queryByProperty(dao: AbstractDao<T, Long>, property: Property, name: String): T =
            dao.queryBuilder().where(property.eq(name)).unique()

    fun queryAll(dao: AbstractDao<T, Long>, properties: Property): List<T> =
            dao.queryBuilder()
                    .orderDesc(properties)
                    .list()
}