package com.arturia.astolfo.ui.tag

import com.arturia.astolfo.data.model.Tag
import com.arturia.astolfo.ui.base.mvp.BasePresenter
import com.arturia.astolfo.ui.base.mvp.BaseView

/**
 * Author: Arturia
 * Date: 2017/11/15
 */
interface TagContract {

    interface View : BaseView<Presenter> {

        fun showLoading()

        fun hideLoading()

        fun handleError(error: Throwable)

        fun onTagLoaded(tags: List<Tag>)
    }

    interface Presenter : BasePresenter {

        fun loadAnimeTag()
    }
}