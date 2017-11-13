package com.arturia.astolfo.ui.comment

import com.arturia.astolfo.data.model.Comment
import com.arturia.astolfo.ui.mvp.BasePresenter
import com.arturia.astolfo.ui.mvp.BaseView

/**
 * Author: Arturia
 * Date: 2017/11/13
 */
interface CommentContract {

    interface View : BaseView<Presenter> {

        fun showLoading()

        fun hideLoading()

        fun handleError(error: Throwable)

        fun onCommentsLoaded(comments: List<Comment>)
    }

    interface Presenter : BasePresenter {

        fun loadComment(number: String, page: String)
    }
}