package com.example.basecoder.Dialog;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.StringRes;
import androidx.fragment.app.FragmentActivity;

import com.example.basecoder.Dialog.BaseDialog.BaseDialog;
import com.example.basecoder.Dialog.BaseDialog.UIBaseDialogFragment;
import com.example.basecoder.R;

/**
 * Create BY Luck-S ON 11:31
 * Email: fine9987@163.com
 * Package:com.example.basecoder.Dialog
 * Description:
 */
public final class ToastDialog {
    public static final class Builder extends UIBaseDialogFragment.Builder<Builder>
            implements Runnable, BaseDialog.OnShowListener {
        private final TextView mMessageView;
        private final ImageView mIconView;
        private Type mType = Type.WARN;
        private int mDuration = 2000;

        public Builder(FragmentActivity activity) {
            super(activity);
            setContentView(R.layout.dialog_toast);
            setAnimStyle(BaseDialog.AnimStyle.TOAST);
            setBackgroundDimEnabled(false);
            setCancelable(false);
            mMessageView = findViewById(R.id.tv_toast_message);
            mIconView = findViewById(R.id.iv_toast_icon);
            addOnShowListener(this);
        }

        public Builder setType(Type type) {
            mType = type;
            switch (type) {
                case FINISH:
                    mIconView.setImageResource(R.drawable.ic_dialog_finish);
                    break;
                case ERROR:
                    mIconView.setImageResource(R.drawable.ic_dialog_error);
                    break;
                case WARN:
                    mIconView.setImageResource(R.drawable.ic_dialog_warning);
                    break;
                default:
                    break;
            }
            return this;
        }

        public Builder setDuration(int duration) {
            mDuration = duration;
            return this;
        }

        public Builder setMessage(@StringRes int id) {
            return setMessage(getString(id));
        }

        public Builder setMessage(CharSequence text) {
            mMessageView.setText(text);
            return this;
        }

        @Override
        public BaseDialog create() {
            if (mType == null) {
                throw new IllegalArgumentException("Must specify a type");
            }
            if ("".equals(mMessageView.getText().toString())) {
                throw new IllegalArgumentException("Message have to Not Null");
            }
            return super.create();
        }

        @Override
        public void onShow(BaseDialog dialog) {
            postDelayed(this, mDuration);
        }

        @Override
        public void run() {
            if (getDialogFragment() != null &&
                    getDialogFragment().isAdded() &&
                    getDialog() != null &&
                    getDialog().isShowing()) {
                dismiss();
            }
        }
    }

    public enum Type {
        FINISH, ERROR, WARN
    }
}
