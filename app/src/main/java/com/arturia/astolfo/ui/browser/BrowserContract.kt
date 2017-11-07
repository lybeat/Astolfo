package com.arturia.astolfo.ui.browser

import com.arturia.astolfo.data.model.Anime
import com.arturia.astolfo.ui.mvp.BasePresenter
import com.arturia.astolfo.ui.mvp.BaseView

/**
 * Author: Arturia
 * Date: 2017/11/7
 */
interface BrowserContract {

    interface View : BaseView<Presenter> {

        fun showLoading()

        fun hideLoading()

        fun handleError(error: Throwable)

        fun onAnimeLoaded(animeList: List<Anime>)
    }

    interface Presenter : BasePresenter {

        fun loadAnimeBrowser(sort: String, page: String)
    }
}