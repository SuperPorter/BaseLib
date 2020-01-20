package com.example.basehttp.InterFace;

import io.reactivex.disposables.Disposable;

/**
 * Create BY Luck-S ON 10:24
 * Email: fine9987@163.com
 * Package:com.example.basehttp.InterFace
 * Description: 自定义请求返回接口
 */
public interface ISubScriber<T> {

    void Subscribe(Disposable d);

    void Error(String errorMsg);

    void Next(T t);

    void Completed();
}
