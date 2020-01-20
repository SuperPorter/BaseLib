package com.example.basehttp;


import com.example.basehttp.Exception.ApiException;
import com.example.basehttp.InterFace.ISubScriber;
import com.example.basehttp.Manager.RxHttpManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Create BY Luck-S ON 10:22
 * Email: fine9987@163.com
 * Package:com.example.basehttp
 * Description:
 */
public abstract class BaseObserver<T> implements Observer<T>, ISubScriber<T> {
    protected String setTag() {
        return null;
    }

    @Override
    public void onSubscribe(Disposable d) {
        RxHttpManager.get().add(setTag(), d);
        Subscribe(d);
    }

    @Override
    public void onNext(T t) {
        Next(t);
    }

    @Override
    public void onError(Throwable e) {
        String message = ApiException.handleException(e).getMessage();
        Error(message);
    }

    @Override
    public void onComplete() {
        Completed();
    }

    private void setError(String errorMsg) {
        Error(errorMsg);
    }
}
