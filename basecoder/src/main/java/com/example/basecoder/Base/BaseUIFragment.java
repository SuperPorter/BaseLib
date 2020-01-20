package com.example.basecoder.Base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.example.basecoder.BaseUtils.StatusManager;
import com.example.basecoder.Toast.ToastUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Create BY Luck-S ON 16:37
 * Email: fine9987@163.com
 * Package:com.example.basecoder.Base
 * Description:
 */
public abstract class BaseUIFragment<A extends BaseUIActivity>
        extends BaseLazyFragment<A>
        implements OnTitleBarListener {
    /**
     * 标题栏对象
     */
    private TitleBar mTitleBar;
    /**
     * 状态栏沉浸
     */
    private ImmersionBar mImmersionBar;
    /**
     * ButterKnife 注解
     */
    private Unbinder mButterKnife;

    protected int getTitleId() {
        return 0;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if (view != null) {
            mButterKnife = ButterKnife.bind(this, view);
        }
        return view;
    }

    @Override
    protected void initFragment() {
        if (getTitleId() > 0) {
            View view = findViewById(getTitleId());
            if (view instanceof TitleBar) {
                mTitleBar = (TitleBar) view;
            }
        } else if (getTitleId() == 0 && getView() instanceof ViewGroup) {
            mTitleBar = BaseUIActivity.findTitleBar((ViewGroup) getView());
        }
        if (mTitleBar != null) {
            mTitleBar.setOnTitleBarListener(this);
        }
        initImmersion();
        super.initFragment();
    }

    protected void initImmersion() {
        if (isStatusBarEnabled()) {
            statusBarConfig().init();
            if (getTitleId() > 0) {
                ImmersionBar.setTitleBar(this, findViewById(getTitleId()));
            } else if (mTitleBar != null) {
                ImmersionBar.setTitleBar(this, mTitleBar);
            }
        }
    }

    public boolean isStatusBarEnabled() {
        return false;
    }

    protected ImmersionBar getStatusBarConfig() {
        return mImmersionBar;
    }

    protected boolean statusBarDarkFont() {
        // 返回真表示黑色字体
        return true;
    }

    private ImmersionBar statusBarConfig() {
        mImmersionBar = ImmersionBar.with(this)
                .statusBarDarkFont(statusBarDarkFont())
                .keyboardEnable(true);
        return mImmersionBar;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isStatusBarEnabled() && isLazyLoad()) {
            statusBarConfig().init();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mButterKnife != null) {
            mButterKnife.unbind();
        }
    }

    public void setTitle(@StringRes int id) {
        setTitle(getString(id));
    }

    public void setTitle(CharSequence text) {
        if (mTitleBar != null) {
            mTitleBar.setTitle(text);
        } else {
            getAttachActivity().setTitle(text);
        }
    }

    @Nullable
    public TitleBar getmTitleBar() {
        if (getTitleId() > 0 && findViewById(getTitleId()) instanceof TitleBar) {
            return findViewById(getTitleId());
        }
        return null;
    }

    /**
     * 显示吐司
     */
    public void toast(CharSequence text) {
        ToastUtils.show(text);
    }

    public void toast(@StringRes int id) {
        ToastUtils.show(id);
    }

    public void toast(Object object) {
        ToastUtils.show(object);
    }

    /**
     * TitleBar 左边的View被点击了
     */
    @Override
    public void onLeftClick(View v) {
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

    /**
     * 多状态切换布局
     */
    private final StatusManager mStatusManager = new StatusManager();

    public void showLoading() {
        mStatusManager.showLoading(getAttachActivity(), (ViewGroup) getView());
    }

    public void showError(CharSequence text) {
        mStatusManager.showError(getAttachActivity(), (ViewGroup) getView(), text);
    }

    public void showEmpty(CharSequence text) {
        mStatusManager.showEmpty(getAttachActivity(), (ViewGroup) getView(), text);
    }

    public void showNoNet(CharSequence text) {
        mStatusManager.showNoNetWork(getAttachActivity(), (ViewGroup) getView(), text);
    }

    public void showContext() {
        mStatusManager.showContent(getAttachActivity(), (ViewGroup) getView());
    }

}
