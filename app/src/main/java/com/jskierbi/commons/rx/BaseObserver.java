package com.jskierbi.commons.rx;

import android.text.TextUtils;
import android.util.Log;
import rx.Observer;

public class BaseObserver<T> implements Observer<T> {

    private static final String TAG             = BaseObserver.class.getSimpleName();
    private static final String DEFAULT_MESSAGE = "Error in base observable";

    private final String errorMessage;

    public BaseObserver() {
        errorMessage = DEFAULT_MESSAGE;
    }

    public BaseObserver(String errorMessage) {
        this.errorMessage = TextUtils.isEmpty(errorMessage) ? DEFAULT_MESSAGE : errorMessage;
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, errorMessage, e);
    }

    @Override
    public void onNext(T t) {
    }
}
