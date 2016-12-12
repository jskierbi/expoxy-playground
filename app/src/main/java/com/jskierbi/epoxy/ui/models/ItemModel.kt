package com.jskierbi.epoxy.ui.models

import android.view.ViewGroup
import com.airbnb.epoxy.EpoxyModel
import com.jskierbi.epoxy.R
import com.jskierbi.epoxy.modules.books.model.BookModel
import kotlinx.android.synthetic.main.li_item.view.*

/**
 * Created by jakub on 12/12/16.
 */
class ItemModel(val model: BookModel) : EpoxyModel<ViewGroup>() {

    override fun getDefaultLayout() = R.layout.li_item

    override fun bind(view: ViewGroup?) {
        view?.apply {
            uiTitle.text = model.title
            uiSubtitle.text = model.author
        }
    }
}