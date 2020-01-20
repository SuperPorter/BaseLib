package com.example.basecoder.Base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import androidx.multidex.MultiDexApplication;

import com.example.basecoder.Toast.ToastUtils;

import java.util.Set;

/**
 * 创建者：Sunzeyu
 * <br>创建时间：下午 01:51 2019/4/17 017
 * <br>功能描述：
 */
public class BaseApplication extends MultiDexApplication {

    private static BaseApplication application = null;
    private static Context mContext;
    private static String uid = "10001";//用户id，用户唯一标识
    private Set<Activity> allActivities;
    private static ActivityManager activityStack;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        application = this;
        init();
    }

    public static BaseApplication getInstance() {
        return application;
    }

    /**
     * 得到Application环境变量
     *
     * @return
     */
    public static Context getContext() {
        return mContext;
    }

    /**
     * 初始化话app信息
     */
    private void init() {
        ToastUtils.init(this);
    }

    public static ActivityManager getActstack() {
        return activityStack;
    }

    /**
     * 退出app
     */
    public void exitApp() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    /**
     * 退出app
     */
    public void exitAppAll() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    /**
     * 得到登录用户id
     *
     * @return
     */
    public static String getUID() {
        return uid;
    }

    /**
     * 设置用户id
     *
     * @param userID
     */
    public static void setUID(String userID) {
        uid = userID;
    }
}
