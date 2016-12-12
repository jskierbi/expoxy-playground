package com.jskierbi.epoxy.ui

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import com.hftopto.commons.kt_ext.ioMain
import com.hftopto.commons.kt_ext.logd
import com.hftopto.commons.kt_ext.loge
import com.jskierbi.epoxy.R
import com.jskierbi.epoxy.base.BaseActivity
import com.jskierbi.epoxy.modules.books.model.BookModel
import kotlinx.android.synthetic.main.activity_main.*
import rx.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainActivity : BaseActivity(R.layout.activity_main) {

    @Inject lateinit var activity: FragmentActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)

        val adapter = MainScreenAdapter()

        uiRecyclerView.layoutManager = LinearLayoutManager(this)
        uiRecyclerView.adapter = adapter

        Observable.just(
            BookModel("Surely you're joking, mr. Feynman!", "Richard P. Feynman"),
            BookModel("Harda", "Elzbieta Herezinska"),
            BookModel("The Firm", "John Grisham"),
            BookModel("Power of Habit", "???"),
            BookModel("Little life", "Hanna Yanagihara")
        ).toList().delay(3, TimeUnit.SECONDS).ioMain().subscribe ({ items ->
            logd("Got books!")
            adapter.addBooks(items)
        }, { error ->
            loge("Books error!!!", error)
        })
    }
}
