package com.example.basecoder.Dialog;

import androidx.fragment.app.FragmentActivity;

import com.example.basecoder.Dialog.BaseDialog.UIBaseDialogFragment;
import com.example.basecoder.R;

/**
 * Create BY Luck-S ON 16:18
 * Email: fine9987@163.com
 * Package:com.example.basecoder.Dialog
 * Description:
 */
public final class SeachMenuDialog {
    public static final class Builder extends UIBaseDialogFragment.Builder<Builder> {
        public Builder(FragmentActivity activity) {
            super(activity);
            setContentView(R.layout.seachmenu_dialog);
        }
    }
}
