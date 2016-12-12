package com.hftopto.commons.kt_ext

import rx.Observable
import rx.android.schedulers.AndroidSchedulers.mainThread
import rx.schedulers.Schedulers.io

inline fun <reified T> singleOnSubscribe(crossinline body: () -> T): Observable<T> {
  return Observable.create<T> { subscriber ->
    try {
      subscriber.onNext(body())
      subscriber.onCompleted()
    } catch(e: Exception) {
      subscriber.onError(e)
    }
  }
}

fun <T> Observable<T>.subscribeIoObserveMain() = subscribeOn(io()).observeOn(mainThread())

fun <T> Observable<T>.ioMain() = subscribeOn(io()).observeOn(mainThread())
