package com.example.baselib_adnroidx.UI;

import android.view.Gravity;
import android.view.View;

import com.example.basecoder.Base.BaseUIActivity;
import com.example.basecoder.Dialog.BaseDialog.BaseDialog;
import com.example.basecoder.Dialog.FullMenuDialog;
import com.example.basecoder.Dialog.MenuDialog;
import com.example.baselib_adnroidx.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;

public class Home extends BaseUIActivity {
    @BindView(R.id.home_floatbuttn)
    FloatingActionButton homeFloatbuttn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        setTitle("123");
        homeFloatbuttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FullMenuDialog.Builder(getActivity())
                        .setList("加载", "错误", "断网", "空", "恢复")
                        .setTitle("请选择加载的状态")
                        .setListener(new MenuDialog.OnListener() {
                            @Override
                            public void onSelected(BaseDialog dialog, int position, Object o) {
                                switch (position) {
                                    case 0:
                                        showLoading();
                                        break;
                                    case 1:
                                        showError("发生了一点错误");
                                        break;
                                    case 2:
                                        showNoNet("请检查您的网络状态");
                                        break;
                                    case 3:
                                        showEmpty("暂时没有请求到数据");
                                        break;
                                    case 4:
                                        showContext();
                                        break;
                                }
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .setGravity(Gravity.BOTTOM)
                        .setAnimStyle(BaseDialog.AnimStyle.BOTTOM)
                        .show();
            }
        });
    }

    @Override
    protected void initData() {

    }
}
