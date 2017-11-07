package com.arturia.astolfo.ui.anime

import com.arturia.astolfo.data.model.Anime
import com.arturia.astolfo.ui.mvp.BasePresenter
import com.arturia.astolfo.ui.mvp.BaseView

/**
 * Author: Arturia
 * Date: 2017/11/7
 */
interface AnimeContract {

    interface View : BaseView<Presenter> {

        fun showLoading()

        fun hideLoading()

        fun handleError(error: Throwable)

        fun onAnimeLoaded(Anime: Anime)
    }

    interface Presenter : BasePresenter {

        fun loadAnime(subject: String)
    }
}