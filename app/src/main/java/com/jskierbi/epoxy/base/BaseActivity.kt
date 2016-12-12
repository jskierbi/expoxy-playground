package com.jskierbi.epoxy.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.jskierbi.commons.EventBus
import com.jskierbi.epoxy.base.BaseApplication
import com.jskierbi.epoxy.d2.ActivityComponent
import rx.subscriptions.Subscriptions

abstract class BaseActivity(@LayoutRes private val layoutRes: Int = -1) : AppCompatActivity() {
  val component: ActivityComponent by lazy { (applicationContext as BaseApplication).activityComponent(this) }

  protected var dialogSubscription = Subscriptions.empty()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (layoutRes != -1) setContentView(layoutRes)
  }

  override fun onStart() {
    super.onStart()
    EventBus.register(this)
  }

  override fun onStop() {
    dialogSubscription.unsubscribe()
    EventBus.unregister(this)
    super.onStop()
  }
}