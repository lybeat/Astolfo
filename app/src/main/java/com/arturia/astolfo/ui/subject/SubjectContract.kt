package com.arturia.astolfo.ui.subject

import com.arturia.astolfo.data.model.Subject
import com.arturia.astolfo.ui.mvp.BasePresenter
import com.arturia.astolfo.ui.mvp.BaseView

/**
 * Author: Arturia
 * Date: 2017/11/7
 */
interface SubjectContract {

    interface View : BaseView<Presenter> {

        fun showLoading()

        fun hideLoading()

        fun handleError(error: Throwable)

        fun onSubjectLoaded(subject: Subject)
    }

    interface Presenter : BasePresenter {

        fun loadSubject(number: String)
    }
}