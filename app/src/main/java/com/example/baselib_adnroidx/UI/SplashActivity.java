package com.example.baselib_adnroidx.UI;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.basecoder.Base.BaseUIActivity;
import com.example.baselib_adnroidx.R;
import com.example.baselib_adnroidx.Tools.AppConfig;
import com.gyf.immersionbar.BarHide;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;

import java.util.List;

import butterknife.BindView;

/**
 * Create BY Luck-S ON 11:53
 * Email: fine9987@163.com
 * Package:com.example.baselib_adnroidx.UI
 * Description: 闪屏页面
 */
public class SplashActivity extends BaseUIActivity implements OnPermission, Animation.AnimationListener {
    private static final int ANIM_TIME = 1000;
    @BindView(R.id.iv_splash_bg)
    AppCompatImageView mImageView;
    @BindView(R.id.iv_splash_icon)
    View mIconView;
    @BindView(R.id.iv_splash_name)
    View mNameView;

    @BindView(R.id.tv_splash_debug)
    AppCompatTextView mDebugView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        AlphaAnimation aa = new AlphaAnimation(0.4f, 1.0f);
        aa.setDuration(ANIM_TIME * 2);
        aa.setAnimationListener(this);
        mImageView.startAnimation(aa);
        getStatusBarConfig()
                .fullScreen(true)
                .hideBar(BarHide.FLAG_HIDE_STATUS_BAR)
                .transparentNavigationBar()
                .init();

    }

    @Override
    protected void initData() {
        if (AppConfig.isDebug()) {
            mDebugView.setVisibility(View.VISIBLE);
        } else {
            mDebugView.setText("V" + AppConfig.getVersionName());
        }
    }

    private void requestPermission() {
        XXPermissions.with(this)
                .permission(Permission.Group.STORAGE)
                .request(this);
    }

    @Override
    public void onBackPressed() {
    }


    @Override
    public void hasPermission(List<String> granted, boolean isAll) {
        startActivityFinish(Home.class);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (XXPermissions.isHasPermission(SplashActivity.this, Permission.Group.STORAGE)) {
            hasPermission(null, true);
        } else {
            requestPermission();
        }
    }

    @Override
    public void noPermission(List<String> denied, boolean quick) {
        if (quick) {
            toast(R.string.common_permission_fail);
            XXPermissions.gotoPermissionSettings(SplashActivity.this, true);
        } else {
            toast(R.string.common_permission_hint);
            postDelayed(this::requestPermission, 1000);
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        requestPermission();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
