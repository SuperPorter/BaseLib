package com.example.basehttp.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.basehttp.HttpUtils;

/**
 * Description: TODO
 * author: Luck-s
 * date: 2020/1/17
 * version: V1.0
 */
public class NetUtils {
    /**
     * 判断是否有网络
     *
     * @return 返回值
     */
    public static boolean isNetworkConnected() {
        Context context = HttpUtils.getContext();
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
}
