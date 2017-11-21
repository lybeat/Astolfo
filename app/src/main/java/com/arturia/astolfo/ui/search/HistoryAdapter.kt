package com.arturia.astolfo.ui.search

import com.arturia.astolfo.data.model.SearchHistory
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * Author: Arturia
 * Date: 2017/11/21
 */
class HistoryAdapter(data: List<SearchHistory>?) : BaseQuickAdapter<SearchHistory, BaseViewHolder>(R.layout.item_history, data) {

    override fun convert(helper: BaseViewHolder?, item: SearchHistory?) {
        helper?.setText(R.id.tv_history, item?.name)
    }
}