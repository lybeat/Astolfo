package com.arturia.astolfo.ui.tag

import com.arturia.astolfo.data.model.Anime
import com.arturia.astolfo.ui.base.mvp.BasePresenter
import com.arturia.astolfo.ui.base.mvp.BaseView

/**
 * Author: Arturia
 * Date: 2017/11/16
 */
interface TagAnimeContract {

    interface View : BaseView<Presenter> {

        fun showLoading()

        fun hideLoading()

        fun handleError(error: Throwable)

        fun onAnimeListLoaded(animeList: List<Anime>)
    }

    interface Presenter : BasePresenter {

        fun loadAnimeList(name: String, page: String)
    }
}