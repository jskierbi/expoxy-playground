package com.jskierbi.epoxy.d2

import com.jskierbi.epoxy.ui.MainActivity
import com.jskierbi.epoxy.d2.scope.ActivityScope
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(ActivityModule::class))
@ActivityScope
interface ActivityComponent {
    fun inject(injected: MainActivity)
}
