package com.jskierbi.commons

import android.os.Parcel
import android.os.Parcelable

val <T : Parcelable> T.parcelCopy: T get() = Parcel.obtain().run {
    writeParcelable(this@parcelCopy, 0)
    setDataPosition(0)
    val copy: T = readParcelable(javaClass.classLoader)
    recycle()
    copy
}
