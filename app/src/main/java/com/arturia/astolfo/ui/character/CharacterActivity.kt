package com.arturia.astolfo.ui.character

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.arturia.astolfo.R
import com.arturia.astolfo.data.model.Character
import com.arturia.astolfo.ui.base.SwipeActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_character.*

/**
 * Author: Arturia
 * Date: 2017/11/13
 */
class CharacterActivity : SwipeActivity(), CharacterContract.View {

    private lateinit var presenter: CharacterContract.Presenter

    companion object {
        fun launch(context: Context, href: String?) {
            val intent = Intent(context, CharacterActivity::class.java)
            intent.putExtra("href", href)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener({ finish() })

        val href = intent.getStringExtra("href")
        val temp = href.split("/")
        if (temp.size > 2) {
            val number = temp[2]
            CharacterPresenter(this).loadCharacter(number)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

    override fun setPresenter(presenter: CharacterContract.Presenter) {
        this.presenter = presenter
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun handleError(error: Throwable) {
    }

    override fun onCharacterLoaded(character: Character) {
        toolbar.title = character.name
        tv_info.text = character.info
        tv_summary.text = character.summary
        tv_info_tip.visibility = View.VISIBLE
        tv_summary_tip.visibility = View.VISIBLE

        Glide.with(this)
                .load("http://" + character.avatar)
                .apply(RequestOptions().placeholder(R.drawable.bg_placeholder).error(R.drawable.bg_placeholder))
                .into(iv_avatar)
    }
}