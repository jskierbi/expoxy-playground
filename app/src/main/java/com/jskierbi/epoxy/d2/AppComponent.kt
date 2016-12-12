package com.jskierbi.epoxy.d2

import com.jskierbi.epoxy.base.BaseApplication
import com.jskierbi.epoxy.d2.scope.ApplicationScope
import com.jskierbi.epoxy.d2.AppModule
import dagger.Component

@ApplicationScope
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun plusActivity(activityModule: ActivityModule): ActivityComponent

    fun inject(app: BaseApplication)
}