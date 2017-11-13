package com.arturia.astolfo.ui.character

import com.arturia.astolfo.data.model.Character
import com.arturia.astolfo.ui.mvp.BasePresenter
import com.arturia.astolfo.ui.mvp.BaseView

/**
 * Author: Arturia
 * Date: 2017/11/13
 */
interface CharacterContract {

    interface View : BaseView<Presenter> {

        fun showLoading()

        fun hideLoading()

        fun handleError(error: Throwable)

        fun onCharacterLoaded(character: Character)
    }

    interface Presenter : BasePresenter {

        fun loadCharacter(number: String)
    }
}