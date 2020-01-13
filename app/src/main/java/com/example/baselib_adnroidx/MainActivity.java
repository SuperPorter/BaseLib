package com.example.baselib_adnroidx;

import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.example.basecoder.Base.BaseActivity;

public class MainActivity extends BaseActivity {
    @Override
    public int BindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public Builder createConstant() {
        return new Builder()
                .StatusBarColor(false)
                .CustomizeTitle(findViewById(R.id.main_toolbar));
    }

    @Override
    public void WidgetClick(View view) {

    }
}
