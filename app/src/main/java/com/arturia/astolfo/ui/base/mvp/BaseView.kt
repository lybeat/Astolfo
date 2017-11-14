package com.arturia.astolfo.ui.base.mvp

/**
 * Author: Arturia
 * Date: 2017/10/30
 */
interface BaseView<T> {

    fun setPresenter(presenter: T)
}