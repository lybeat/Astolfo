package com.arturia.astolfo.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Author: Arturia
 * Date: 2017/10/31
 */
abstract class BaseFragment : Fragment() {

    protected abstract fun getView(inflater: LayoutInflater?, container: ViewGroup?, attachToRoot: Boolean): View?

    protected abstract fun initData()

    protected abstract fun initView()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View? = getView(inflater, container, false)

        initData()
        initView()

        return view
    }
}