package com.example.basecoder.Widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * Create BY Luck-S ON 16:53
 * Email: fine9987@163.com
 * Package:com.example.basecoder.Widget
 * Description:
 */
public class NoScrollViewPager extends ViewPager {
    public NoScrollViewPager(@NonNull Context context) {
        super(context);
    }

    public NoScrollViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean executeKeyEvent(@NonNull KeyEvent event) {
        return false;
    }

    @Override
    public void setCurrentItem(int item) {
        boolean smoothScroll;
        int currentItem = getCurrentItem();
        if (currentItem == 0) {
            smoothScroll = item == currentItem + 1;
        } else if (currentItem == getConunt() - 1) {
            smoothScroll = item == currentItem - 1;
        } else {
            smoothScroll = Math.abs(currentItem - item) == 1;
        }
        super.setCurrentItem(item, smoothScroll);
    }

    public int getConunt() {
        PagerAdapter adapter = getAdapter();
        return adapter != null ? adapter.getCount() : 0;
    }
}
