package com.arturia.astolfo.ui.mvp

/**
 * Author: Arturia
 * Date: 2017/10/30
 */
interface BaseView<T> {

    fun setPresenter(presenter: T)
}