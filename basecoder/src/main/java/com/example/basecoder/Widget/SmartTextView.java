package com.example.basecoder.Widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * Create BY Luck-S ON 10:48
 * Email: fine9987@163.com
 * Package:com.example.basecoder.Widget
 * Description:
 */
public class SmartTextView extends AppCompatTextView implements TextWatcher {
    public SmartTextView(Context context) {
        super(context);
        initialize();
    }

    public SmartTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public SmartTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        addTextChangedListener(this);
        // 触发一次监听
        afterTextChanged(null);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        //没有文本的话就自动隐藏这个View
        if ("".equals(getText().toString())) {
            if (getVisibility() != GONE) {
                setVisibility(GONE);
            }
        } else {
            if (getVisibility() != VISIBLE) {
                setVisibility(VISIBLE);
            }
        }
    }
}
