package com.example.basecoder.Base;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.basecoder.BaseUI.MultipleStatusView;
import com.example.basecoder.R;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.bar.TitleBar;

/**
 * Create BY Luck-S ON 10:13
 * Email: fine9987@163.com
 * Package:com.example.basecoder
 * Description: 全部封装完成的页面，没有自定义的空间,如果想自定义的话请使用BaseActivity
 */
public abstract class BaseToolbarActivity extends AppCompatActivity implements View.OnClickListener {
    private ImmersionBar mimmersionBar;
    private MultipleStatusView base_view;
    protected Builder mbuilder;
    protected TitleBar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //先初始化所有View
        setContentView(R.layout.base_activity_layout);
        initBaseView();
        //初始化所有的常量
        mbuilder = createConstant();
        //初始化剩下的变量
        initTitle();
        initView();
        initData();
    }

    public void setToolbarName() {

    }

    public void showLoading() {
        base_view.showLoading();
    }

    public void showError(String msg) {
        base_view.showError(msg);
    }

    public void showNoNetWork(String msg) {
        base_view.showNoNetwork(msg);
    }

    public void showContent() {
        base_view.showContent();
    }

    public void showEmpty(String msg) {
        base_view.showError(msg);
    }

    private void initBaseView() {
        base_view = findViewById(R.id.base_view);
        base_view.setContentViewResId(BindLayout());
        base_view.setOnRetryClickListener(mRetryClickListener);
        mToolbar = findViewById(R.id.base_toolbar);
    }

    final View.OnClickListener mRetryClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "您点击了重试视图", Toast.LENGTH_SHORT).show();
            initData();
        }
    };

    private void initTitle() {
        //初始化沉浸式
        if (mbuilder.getisSetStatusBar()) {
            statusBarConfig().init();
        }
    }

    /**
     * 初始化沉浸式状态栏
     */
    private ImmersionBar statusBarConfig() {
        //在BaseActivity里初始化
        mimmersionBar = ImmersionBar.with(this)
                .statusBarDarkFont(mbuilder.isStatusBarDarkFont())    //默认状态栏字体颜色为黑色
                .keyboardEnable(false, WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
                        | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);  //解决软键盘与底部输入框冲突问题，默认为false，还有一个重载方法，可以指定软键盘mode
        return mimmersionBar;
    }

    //销毁的时候
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 获取布局ID
     */
    public abstract int BindLayout();

    /**
     * 初始化View
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData();

    public abstract Builder createConstant();

    private boolean fastClick() {
        long lastClick = 0;
        if (System.currentTimeMillis() - lastClick <= 1000) {
            return false;
        }
        lastClick = System.currentTimeMillis();
        return true;
    }

    public abstract void WidgetClick(View view);

    @Override
    public void onClick(View v) {
        if (fastClick()) {
            WidgetClick(v);
        } else {

        }
    }

    public static final class Builder {
        /**
         * 是否沉浸状态栏
         **/
        private boolean isSetStatusBar = true;
        /**
         * 是否允许全屏
         **/
        private boolean mAllowFullScreen = true;
        /**
         * 是否禁止旋转屏幕
         **/
        private boolean isAllowScreenRoate = false;
        /**
         * 白色或者是黑色
         **/
        private boolean statusBarDarkFont = false;

        /**
         * @param isHaveStatusBar
         * @return
         */

        public Builder StatusBar(boolean isHaveStatusBar) {
            this.isSetStatusBar = isHaveStatusBar;
            return this;
        }

        public Builder allowFullScreen(boolean isHaveAllFullScreen) {
            this.mAllowFullScreen = isHaveAllFullScreen;
            return this;
        }

        public Builder allowScreenRoate(boolean isHaveAllowScreenRoate) {
            this.isAllowScreenRoate = isHaveAllowScreenRoate;
            return this;
        }


        public Builder StatusBarColor(boolean statusBarDarkFont) {
            this.statusBarDarkFont = statusBarDarkFont;
            return this;
        }

        public boolean getisSetStatusBar() {
            return isSetStatusBar;
        }

        public boolean isSetStatusBar() {
            return isSetStatusBar;
        }

        public boolean ismAllowFullScreen() {
            return mAllowFullScreen;
        }

        public boolean isAllowScreenRoate() {
            return isAllowScreenRoate;
        }

        public boolean isStatusBarDarkFont() {
            return statusBarDarkFont;
        }
    }
}
