package com.example.basecoder.Mvp.BaseUI;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.basecoder.Base.BaseToolbarActivity;
import com.example.basecoder.Base.BaseConstant;
import com.example.basecoder.Mvp.BaseInterFace.BaseContract;

/**
 * Create BY Luck-S ON 11:00
 * Email: fine9987@163.com
 * Package:com.example.basecoder.Mvp
 * Description:
 */
public abstract class BaseMvpActivity extends BaseToolbarActivity implements BaseContract.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void loading(boolean isShow) {

    }

    @Override
    public void showMessage(String message, int toastOrDialog) {
        switch (toastOrDialog) {
            case BaseConstant.TOAST:

                break;
            case BaseConstant.DIALOG:

                break;
        }
    }

    @Override
    public void showError(String error, int toastOrDialog) {
        switch (toastOrDialog) {
            case BaseConstant.TOAST:

                break;
            case BaseConstant.DIALOG:

                break;
        }
    }
}
