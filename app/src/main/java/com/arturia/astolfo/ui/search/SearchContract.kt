package com.arturia.astolfo.ui.search

import com.arturia.astolfo.data.model.Anime
import com.arturia.astolfo.ui.base.mvp.BasePresenter
import com.arturia.astolfo.ui.base.mvp.BaseView

/**
 * Author: Arturia
 * Date: 2017/11/16
 */
interface SearchContract {

    interface View : BaseView<Presenter> {

        fun showLoading()

        fun hideLoading()

        fun handleError(error: Throwable)

        fun onSearchLoaded(animeList: List<Anime>)
    }

    interface Presenter : BasePresenter {

        fun loadSearch(name: String, cat: String, page: String)
    }
}