package com.example.basecoder.Base;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.example.basecoder.BaseUtils.ActivityStackManager;
import com.example.basecoder.BaseUtils.StatusManager;
import com.example.basecoder.R;
import com.example.basecoder.Toast.ToastUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Create BY Luck-S ON 14:58
 * Email: fine9987@163.com
 * Package:com.example.basecoder.Base
 * Description:
 */
public abstract class BaseUIActivity extends BaseActivity implements OnTitleBarListener {
    private TitleBar mTitleBar;
    private ImmersionBar mImmersionBar;
    private Unbinder mButterKnife;


    protected int getTitleID() {
        return 0;
    }

    @Override
    protected void initActivity() {
        super.initActivity();
        ActivityStackManager.getInstance().onCreated(this);
    }

    @Override
    protected void initLayout() {
        super.initLayout();
        if (getTitleID() > 0) {
            View view = findViewById(getTitleID());
            if (view instanceof TitleBar) {
                mTitleBar = (TitleBar) view;
            }
        } else if (getTitleID() == 0) {
            mTitleBar = findTitleBar(getContentView());
        }
        if (mTitleBar != null) {
            //设置点击事件等
            mTitleBar.setOnTitleBarListener(this);
        }
        mButterKnife = ButterKnife.bind(this);
        initImmersion();
    }

    static TitleBar findTitleBar(ViewGroup group) {
        for (int i = 0; i < group.getChildCount(); i++) {
            View view = group.getChildAt(i);
            if ((view instanceof TitleBar)) {
                return (TitleBar) view;
            } else if (view instanceof ViewGroup) {
                TitleBar titleBar = findTitleBar((ViewGroup) view);
                if (titleBar != null) {
                    return titleBar;
                }
            }
        }
        return null;
    }

    protected void initImmersion() {
        if (isStatusBarEnabled()) {
            statusBarConfig().init();
            if (getTitleID() > 0) {
                ImmersionBar.setTitleBar(this, findViewById(getTitleID()));
            } else if (mTitleBar != null) {
                ImmersionBar.setTitleBar(this, mTitleBar);
            }
        }
    }

    public boolean isStatusBarEnabled() {
        return true;
    }

    public ImmersionBar getStatusBarConfig() {
        return mImmersionBar;
    }

    public boolean statusBarDarkFont() {
        return true;
    }

    protected ImmersionBar statusBarConfig() {
        mImmersionBar = ImmersionBar.with(this)
                .statusBarDarkFont(statusBarDarkFont());
        return mImmersionBar;
    }

    /**
     * 设置标题栏的标题
     */
    @Override
    public void setTitle(@StringRes int id) {
        setTitle(getString(id));
    }

    /**
     * 设置标题栏的标题
     */
    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        if (mTitleBar != null) {
            mTitleBar.setTitle(title);
        }
    }

    /**
     * 设置标题栏的左标题
     */
    public void setLeftTitle(int id) {
        if (mTitleBar != null) {
            mTitleBar.setLeftTitle(id);
        }
    }

    public void setLeftTitle(CharSequence text) {
        if (mTitleBar != null) {
            mTitleBar.setLeftTitle(text);
        }
    }

    public CharSequence getLeftTitle() {
        if (mTitleBar != null) {
            return mTitleBar.getLeftTitle();
        }
        return "";
    }

    /**
     * 设置标题栏的右标题
     */
    public void setRightTitle(int id) {
        if (mTitleBar != null) {
            mTitleBar.setRightTitle(id);
        }
    }

    public void setRightTitle(CharSequence text) {
        if (mTitleBar != null) {
            mTitleBar.setRightTitle(text);
        }
    }

    public CharSequence getRightTitle() {
        if (mTitleBar != null) {
            return mTitleBar.getRightTitle();
        }
        return "";
    }

    /**
     * 设置标题栏的左图标
     */
    public void setLeftIcon(int id) {
        if (mTitleBar != null) {
            mTitleBar.setLeftIcon(id);
        }
    }

    public void setLeftIcon(Drawable drawable) {
        if (mTitleBar != null) {
            mTitleBar.setLeftIcon(drawable);
        }
    }

    @Nullable
    public Drawable getLeftIcon() {
        if (mTitleBar != null) {
            return mTitleBar.getLeftIcon();
        }
        return null;
    }

    /**
     * 设置标题栏的右图标
     */
    public void setRightIcon(int id) {
        if (mTitleBar != null) {
            mTitleBar.setRightIcon(id);
        }
    }

    public void setRightIcon(Drawable drawable) {
        if (mTitleBar != null) {
            mTitleBar.setRightIcon(drawable);
        }
    }

    @Nullable
    public Drawable getRightIcon() {
        if (mTitleBar != null) {
            return mTitleBar.getRightIcon();
        }
        return null;
    }

    @Nullable
    public TitleBar getTitleBar() {
        return mTitleBar;
    }

    /**
     * {@link OnTitleBarListener}
     */

    /**
     * TitleBar 左边的View被点击了
     */
    @Override
    public void onLeftClick(View v) {
        onBackPressed();
    }

    /**
     * TitleBar 中间的View被点击了
     */
    @Override
    public void onTitleClick(View v) {

    }

    /**
     * TitleBar 右边的View被点击了
     */
    @Override
    public void onRightClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mButterKnife != null) {
            mButterKnife.unbind();
        }
        ActivityStackManager.getInstance().onDestroyed(this);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        overridePendingTransition(R.anim.activity_right_in, R.anim.activity_right_out);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_left_in, R.anim.activity_right_out);
    }

    public void toast(CharSequence text) {
        ToastUtils.show(text);
    }

    public void toast(@StringRes int id) {
        ToastUtils.show(id);
    }

    public void toast(Object o) {
        ToastUtils.show(o);
    }

    private final StatusManager statusManager = new StatusManager();

    public void showLoading() {
        statusManager.showLoading(this, getContentView());
    }

    public void showError(CharSequence text) {
        statusManager.showError(this, getContentView(), text);
    }

    public void showEmpty(CharSequence text) {
        statusManager.showEmpty(this, getContentView(), text);
    }

    public void showNoNet(CharSequence text) {
        statusManager.showNoNetWork(this, getContentView(), text);
    }

    public void showContext() {
        statusManager.showContent(this, getContentView());
    }
}
