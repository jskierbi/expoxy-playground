package com.jskierbi.commons.typedviewholder

import android.support.annotation.LayoutRes
import android.view.ViewGroup
import com.jskierbi.commons.typedviewholder.TypedViewHolder
import com.jskierbi.commons.typedviewholder.TypedViewHolderAdapter

/**
 * Created by q on 26/09/16.
 */
inline fun <T : Any, reified TZ : T> TypedViewHolderAdapter.Builder<T>.registerHolder(crossinline createHolder: (ViewGroup) -> TypedViewHolder<TZ>)
    = addFactory(object : TypedViewHolderFactory<TZ>(TZ::class.java) {
    override fun build(parent: ViewGroup): TypedViewHolder<TZ> = createHolder(parent)
})

inline fun <T : Any, reified TZ : T> TypedViewHolderAdapter.Builder<T>.registerHolder(@LayoutRes layout: Int, crossinline binder: TypedViewHolder<TZ>.(TZ) -> Unit)
    = addFactory(object : TypedViewHolderFactory<TZ>(TZ::class.java) {
    override fun build(parent: ViewGroup): TypedViewHolder<TZ> = object : TypedViewHolder<TZ>(layout, parent) {
        override fun bind(dataItem: TZ) = binder(dataItem)
    }
})
