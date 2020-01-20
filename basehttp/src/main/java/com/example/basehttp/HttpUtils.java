package com.example.basehttp;

import android.app.Application;
import android.content.Context;

import com.example.basehttp.Factory.ApiFactory;

/**
 * Create BY Luck-S ON 10:59
 * Email: fine9987@163.com
 * Package:com.example.basehttp
 * Description:
 */
public class HttpUtils {
    private static HttpUtils instance;
    private static Application context;

    public static HttpUtils getInstance() {
        if (instance == null) {
            synchronized (HttpUtils.class) {
                if (instance == null) {
                    instance = new HttpUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 初始化方法，在Application中初始化
     */
    public HttpUtils init(Application application) {
        context = application;
        return this;
    }

    /**
     * 获取上下文
     */
    public static Context getContext() {
        if (context == null) {
            throw new ExceptionInInitializerError("Please initialize HttpUtils");
        }
        return context;
    }

    public ApiFactory config() {
        if (context == null) {
            throw new ExceptionInInitializerError("Please initialize HttpUtils");
        }
        return ApiFactory.getInstance();
    }

    public static <K> K createApi(Class<K> cls) {
        return ApiFactory.getInstance().createApi(cls);
    }


}
