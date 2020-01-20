package com.example.basecoder.Dialog.BaseDialog;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.FragmentActivity;

import com.example.basecoder.Toast.ToastUtils;

import butterknife.ButterKnife;

/**
 * Description: TODO
 * author: Luck-s
 * date: 2020/1/15
 * version: V1.0
 */
public class UIBaseDialogFragment {
    public static class Builder<B extends UIBaseDialogFragment.Builder> extends BaseDialogFragment.Builder<B> {
        public Builder(FragmentActivity activity) {
            super(activity);
        }

        @Override
        public B setContentView(@NonNull View view) {
            ButterKnife.bind(this, view);
            return super.setContentView(view);
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
    }

}
