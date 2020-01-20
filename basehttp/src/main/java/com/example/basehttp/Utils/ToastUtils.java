package com.example.basehttp.Utils;

import android.widget.Toast;

import com.example.basehttp.HttpUtils;

/**
 * Description: TODO
 * author: Luck-s
 * date: 2020/1/17
 * version: V1.0
 */

public class ToastUtils {

    private static Toast mToast;

    /**
     * Toast提示
     *
     * @param msg 提示内容
     */
    public static void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(HttpUtils.getContext(), msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }
}
