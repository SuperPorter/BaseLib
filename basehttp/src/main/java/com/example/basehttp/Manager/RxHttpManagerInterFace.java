package com.example.basehttp.Manager;

import io.reactivex.disposables.Disposable;

/**
 * Create BY Luck-S ON 10:28
 * Email: fine9987@163.com
 * Package:com.example.basehttp.Manager
 * Description:
 */
public interface RxHttpManagerInterFace<T> {
    //添加一个请求
    void add(T tag, Disposable disposable);

    //删除一个请求
    void remove(T tag);

    //取消一个请求
    void cancel(T tag);

    //取消一些请求
    void cancel(T... tags);

    //取消所有请求
    void cancelAll();
}
