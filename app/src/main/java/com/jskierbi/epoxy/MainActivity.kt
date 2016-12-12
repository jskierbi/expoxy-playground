package com.jskierbi.epoxy

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.jskierbi.epoxy.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity(R.layout.activity_main) {
    @Inject lateinit var activity: FragmentActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
    }
}
