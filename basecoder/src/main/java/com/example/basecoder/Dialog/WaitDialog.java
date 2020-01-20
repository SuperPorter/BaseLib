package com.example.basecoder.Dialog;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.StringRes;
import androidx.fragment.app.FragmentActivity;

import com.example.basecoder.Dialog.BaseDialog.BaseDialog;
import com.example.basecoder.Dialog.BaseDialog.UIBaseDialogFragment;
import com.example.basecoder.R;

/**
 * Create BY Luck-S ON 11:19
 * Email: fine9987@163.com
 * Package:com.example.basecoder.Dialog
 * Description:
 */
public final class WaitDialog {
    public static final class Builder extends UIBaseDialogFragment.Builder<Builder> {
        private final TextView mMessageView;

        public Builder(FragmentActivity activity) {
            super(activity);
            setContentView(R.layout.dialog_wait);
            setAnimStyle(BaseDialog.AnimStyle.TOAST);
            setBackgroundDimEnabled(false);
            setCancelable(false);
            mMessageView = findViewById(R.id.tv_wait_message);
        }

        public Builder setMessage(@StringRes int id) {
            return setMessage(getString(id));
        }

        public Builder setMessage(CharSequence text) {
            mMessageView.setText(text);
            mMessageView.setVisibility(text == null ? View.GONE : View.VISIBLE);
            return this;
        }

    }
}
