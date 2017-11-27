package com.arturia.astolfo.ui.tag

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arturia.astolfo.R
import com.arturia.astolfo.data.model.Tag
import com.arturia.astolfo.ui.base.BaseFragment
import com.arturia.astolfo.ui.main.FragmentListener
import com.arturia.astolfo.ui.search.SearchActivity
import kotlinx.android.synthetic.main.fragment_tag.*

/**
 * Author: Arturia
 * Date: 2017/11/7
 */
class TagFragment : BaseFragment(), TagContract.View, Toolbar.OnMenuItemClickListener {

    private lateinit var presenter: TagContract.Presenter
    private lateinit var listener: FragmentListener

    private val tags = mutableListOf<Tag>()
    private var index = 0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.fragment_tag, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setNavigationIcon(R.drawable.ic_menu)
        toolbar.setNavigationOnClickListener { listener.onNavigationClick() }
        toolbar.setOnMenuItemClickListener(this)

        fab_refresh.setOnClickListener {
            if (index + 25 > tags.size) {
                index = 0
                Toast.makeText(activity, "已浏览所有标签", Toast.LENGTH_SHORT).show()
            }
            val tempTags = tags.subList(index, index + 25)
            tag_cloud.setAdapter(TagAdapter(tempTags))
            index += 25
        }

        TagPresenter(this).loadAnimeTag()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is FragmentListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement FragmentListener")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_search) {
            SearchActivity.launch(activity)
        }

        return super.onOptionsItemSelected(item)
    }

    override fun setPresenter(presenter: TagContract.Presenter) {
        this.presenter = presenter
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun handleError(error: Throwable) {
        error.printStackTrace()
    }

    override fun onTagLoaded(tags: List<Tag>) {
        fab_refresh.visibility = View.VISIBLE
        this.tags.addAll(tags)
        val tempTags = this.tags.subList(0, 25)
        tag_cloud.setAdapter(TagAdapter(tempTags))
        index += 25
    }
}