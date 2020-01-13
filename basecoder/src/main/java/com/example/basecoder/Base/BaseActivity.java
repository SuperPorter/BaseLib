package com.example.basecoder.Base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.basecoder.R;

/**
 * Create BY Luck-S ON 10:13
 * Email: fine9987@163.com
 * Package:com.example.basecoder
 * Description:
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_layout);
    }
}
