package com.jskierbi.commons.typedviewholder

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class TypedViewHolder<T> : RecyclerView.ViewHolder {

    protected var context: Context

    constructor(@LayoutRes layoutRes: Int, parent: ViewGroup) : super(LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)) {
        context = parent.context
    }

    constructor(view: View) : super(view) {
        context = view.context
    }

    abstract fun bind(dataItem: T)
}
