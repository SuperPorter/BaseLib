package com.example.basecoder.Mvp.BaseInterFace;

/**
 * Create BY Luck-S ON 10:59
 * Email: fine9987@163.com
 * Package:com.example.basecoder.Mvp
 * Description:
 */
public interface BaseContract {
    interface View {
        /**
         * @param isShow 是否显示
         */
        void loading(boolean isShow);

        /**
         * @param message       信息内容
         * @param toastOrDialog BaseConstant.Toast BaseConstant.Dialog
         */
        void showMessage(String message, int toastOrDialog);

        /**
         * @param error         错误信息
         * @param toastOrDialog BaseConstant.Toast BaseConstant.Dialog
         */
        void showError(String error, int toastOrDialog);
    }

    interface Persenter {

    }

    interface Model {

    }

}


