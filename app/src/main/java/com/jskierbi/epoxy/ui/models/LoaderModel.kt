package com.jskierbi.epoxy.ui.models

import android.support.annotation.LayoutRes
import android.view.View
import com.airbnb.epoxy.EpoxyModel
import com.jskierbi.commons.view.LoadingLayout
import com.jskierbi.epoxy.R

/**
 * Created by jakub on 12/12/16.
 */
class LoaderModel : EpoxyModel<View>() {

    override fun getDefaultLayout() = R.layout.li_loader
}