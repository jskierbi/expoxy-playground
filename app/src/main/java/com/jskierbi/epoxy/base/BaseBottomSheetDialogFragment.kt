package com.jskierbi.epoxy.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jskierbi.epoxy.d2.ActivityComponent
import com.jskierbi.epoxy.base.BaseActivity

/**
 * Created by q on 01/10/16.
 */
abstract class BaseBottomSheetDialogFragment(private @LayoutRes val layoutRes: Int) : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = when (layoutRes) {
        -1 -> null
        else -> inflater.inflate(layoutRes, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//    if (layoutRes == -1) typeface(view) // Apply custom typeface
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context !is BaseActivity) {
            throw IllegalStateException("This view should be attached to Activity instance implementing ActivityComponent providing method");
        } else {
            inject(context.component)
        }
    }

    protected abstract fun inject(component: ActivityComponent)
}