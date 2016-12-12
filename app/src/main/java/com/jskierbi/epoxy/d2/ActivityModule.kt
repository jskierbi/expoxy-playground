package com.jskierbi.epoxy.d2

import android.app.Activity
import android.content.Context
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import com.jskierbi.epoxy.d2.qualifier.ForActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: FragmentActivity) {
    @Provides @ForActivity fun provideActivityContext() = activity as Context
    @Provides fun provideLayoutInflater(activity: Activity) = LayoutInflater.from(activity)
    @Provides fun provideActivity() = activity
}
