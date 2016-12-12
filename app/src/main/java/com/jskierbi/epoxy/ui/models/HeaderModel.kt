package com.jskierbi.epoxy.ui.models

import android.view.ViewGroup
import com.airbnb.epoxy.EpoxyModel
import com.jskierbi.epoxy.R
import kotlinx.android.synthetic.main.li_header.view.*

/**
 * Created by jakub on 12/12/16.
 */
class HeaderModel(val title: String) : EpoxyModel<ViewGroup>() {

    override fun getDefaultLayout() = R.layout.li_header

    override fun bind(view: ViewGroup?) {
        view?.apply {
            uiTitle.text = title
        }
    }
}