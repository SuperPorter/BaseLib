package com.example.baselib_adnroidx.Tools;

import com.example.baselib_adnroidx.BuildConfig;

/**
 * Create BY Luck-S ON 16:13
 * Email: fine9987@163.com
 * Package:com.example.baselib_adnroidx.Tools
 * Description:
 */
public class AppConfig {
    /**
     * 当前是否为 Debug 模式
     */
    public static boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    /**
     * 获取当前应用的包名
     */
    public static String getPackageName() {
        return BuildConfig.APPLICATION_ID;
    }

    /**
     * 获取当前应用的版本名
     */
    public static String getVersionName() {
        return BuildConfig.VERSION_NAME;
    }

    /**
     * 获取当前应用的版本码
     */
    public static int getVersionCode() {
        return BuildConfig.VERSION_CODE;
    }

    /**
     * 获取当前应用的渠道名
     */
    public static String getProductFlavors() {
        return BuildConfig.FLAVOR;
    }
}
