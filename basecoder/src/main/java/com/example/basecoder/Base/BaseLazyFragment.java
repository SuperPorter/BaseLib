package com.example.basecoder.Base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.Random;

/**
 * Create BY Luck-S ON 10:14
 * Email: fine9987@163.com
 * Package:com.example.basecoder
 * Description:
 */
public abstract class BaseLazyFragment<A extends BaseActivity> extends Fragment {
    /**
     * Activity对象
     */
    private A mActivity;
    /**
     * 根布局
     */
    private View mRootView;
    /**
     * 是否进行过懒加载
     */
    private boolean isLazyLoad;
    /**
     * Fragment 是否可见
     */
    private boolean isFragmentVisible;
    /**
     * 是否是 replace Fragment 的形式
     */
    private boolean isReplaceFragment;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (A) requireActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null && getLayoutId() > 0) {
            mRootView = inflater.inflate(getLayoutId(), null);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Nullable
    @Override
    public View getView() {
        return mRootView;
    }

    public A getAttachActivity() {
        return mActivity;
    }

    protected boolean isLazyLoad() {
        return isLazyLoad;
    }

    public boolean isFragmentVisible() {
        return isFragmentVisible;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isReplaceFragment) {
            if (isFragmentVisible) {
                initLazyLoad();
            }
        } else {
            initLazyLoad();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isReplaceFragment = true;
        this.isFragmentVisible = isVisibleToUser;
        if (isVisibleToUser && mRootView != null) {
            if (!isLazyLoad) {
                initLazyLoad();
            } else {
                onRestart();
            }
        }
    }

    protected void initLazyLoad() {
        if (!isLazyLoad) {
            isLazyLoad = true;
            initFragment();
        }
    }

    protected void onRestart() {

    }

    protected void initFragment() {
        initView();
        initData();
    }

    /**
     * 引入布局
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    protected <V extends View> V findViewById(@IdRes int id) {
        return mRootView.findViewById(id);
    }

    protected <V extends View> V findActivityViewById(@IdRes int id) {
        return mActivity.findViewById(id);
    }

    /**
     * startActivity 方法优化
     */

    public void startActivity(Class<? extends Activity> cls) {
        startActivity(new Intent(mActivity, cls));
    }

    public void startActivityFinish(Class<? extends Activity> cls) {
        startActivityFinish(new Intent(mActivity, cls));
    }

    public void startActivityFinish(Intent intent) {
        startActivity(intent);
        finish();
    }

    /**
     * startActivityForResult 方法优化
     */

    private BaseActivity.ActivityCallback mActivityCallback;
    private int mActivityRequestCode;

    public void startActivityForResult(Class<? extends Activity> cls, BaseActivity.ActivityCallback callback) {
        startActivityForResult(new Intent(mActivity, cls), null, callback);
    }

    public void startActivityForResult(Intent intent, BaseActivity.ActivityCallback callback) {
        startActivityForResult(intent, null, callback);
    }

    public void startActivityForResult(Intent intent, Bundle options, BaseActivity.ActivityCallback callback) {
        // 回调还没有结束，所以不能再次调用此方法，这个方法只适合一对一回调，其他需求请使用原生的方法实现
        if (mActivityCallback == null) {
            mActivityCallback = callback;
            // 随机生成请求码，这个请求码在 0 - 255 之间
            mActivityRequestCode = new Random().nextInt(255);
            startActivityForResult(intent, mActivityRequestCode, options);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (mActivityCallback != null && mActivityRequestCode == requestCode) {
            mActivityCallback.onActivityResult(resultCode, data);
            mActivityCallback = null;
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void finish() {
        mActivity.finish();
        mActivity = null;
    }

    public <T> T getSystemService(@NonNull Class<T> serviceClass) {
        return ContextCompat.getSystemService(mActivity, serviceClass);
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        return false;
    }
}
