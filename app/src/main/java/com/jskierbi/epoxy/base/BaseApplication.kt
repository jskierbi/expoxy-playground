package com.jskierbi.epoxy.base

import android.content.Context
import android.support.multidex.MultiDexApplication
import com.jskierbi.epoxy.BuildConfig
import com.jskierbi.epoxy.d2.ActivityModule
import com.jskierbi.epoxy.d2.AppComponent
import com.jskierbi.epoxy.d2.AppModule
import com.jskierbi.epoxy.d2.DaggerAppComponent
import com.jskierbi.epoxy.base.BaseActivity

class BaseApplication : MultiDexApplication() {

    val appComponent: AppComponent by lazy { DaggerAppComponent.builder().appModule(AppModule(this@BaseApplication)).build().apply { inject(this@BaseApplication) } }

    fun activityComponent(activity: BaseActivity) = appComponent.plusActivity(ActivityModule(activity))

    companion object {

        operator fun get(context: Context): BaseApplication {
            return context.applicationContext as BaseApplication
        }
    }
}
