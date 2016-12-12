package com.jskierbi.commons.kt_ext

import android.app.Activity
import android.content.Intent
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast

inline fun FragmentActivity.setupFragment(@IdRes containerId: Int, addToBackstack: Boolean = false, fragment: () -> Fragment)
    = supportFragmentManager.setupFragment(containerId, addToBackstack, fragment)

inline fun FragmentActivity.setupFragmentOptional(@IdRes containerId: Int, addToBackstack: Boolean = false, fragment: () -> Fragment)
    = when (findViewById(containerId)) {
    null -> null
    else -> setupFragment(containerId, addToBackstack, fragment)
}

inline fun <reified T : Fragment> FragmentManager.setupFragment(@IdRes containerId: Int, addToBackstack: Boolean = false, fragment: () -> T): T {
    var fragment = findFragmentById(containerId) as? T
    if (fragment == null) {
        fragment = fragment()
        beginTransaction().apply {
            add(containerId, fragment, if (addToBackstack) fragment?.javaClass?.simpleName else null)
            if (addToBackstack) addToBackStack(fragment?.javaClass?.simpleName)
        }.commit()
    }
    return fragment
}

inline fun <reified T : Activity> Activity.startActivity() = startActivity(Intent(this, T::class.java))
inline fun <reified T : Activity> Fragment.startActivity(): Unit = activity.startActivity<T>()

fun Fragment.onClick(@IdRes id: Int, listener: (View) -> Unit) = view!!.findViewById(id)!!.setOnClickListener { listener(it) }

fun Activity.onClick(@IdRes id: Int, listener: (View) -> Unit) = findViewById(id)!!.setOnClickListener { listener(it) }

fun Fragment.onClickOptional(@IdRes id: Int, listener: (View) -> Unit) = view!!.findViewById(id)?.setOnClickListener { listener(it) }

fun Activity.onClickOptional(@IdRes id: Int, listener: (View) -> Unit) = findViewById(id)?.setOnClickListener { listener(it) }

fun Fragment.onCheckedChanged(@IdRes id: Int, listener: (Boolean) -> Unit)
    = (view!!.findViewById(id)!! as CompoundButton).setOnCheckedChangeListener { button, isChecked -> listener(isChecked) }

fun Activity.onCheckedChanged(@IdRes id: Int, listener: (Boolean) -> Unit)
    = (findViewById(id)!! as CompoundButton).setOnCheckedChangeListener { button, isChecked -> listener(isChecked) }

fun Activity.toast(text: String) = Toast.makeText(this, text, Toast.LENGTH_LONG).show()

fun Fragment.toast(text: String) = Toast.makeText(activity, text, Toast.LENGTH_LONG).show()

private val Any?.unit: Unit get() = Unit

//fun Activity.typeface() = TypefaceHelper.typeface(this)