package com.example.basecoder.BaseUtils;

import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentActivity;

import com.example.basecoder.BaseUI.MultipleStatusView;

/**
 * Create BY Luck-S ON 17:29
 * Email: fine9987@163.com
 * Package:com.example.basecoder.BaseUtils
 * Description:
 */
public class StatusManager {
    private MultipleStatusView multipleStatusView;

    private static MultipleStatusView findStatusView(ViewGroup group) {
        for (int i = 0; i < group.getChildCount(); i++) {
            View view = group.getChildAt(i);
            if ((view instanceof MultipleStatusView)) {
                return (MultipleStatusView) view;
            } else if (view instanceof ViewGroup) {
                MultipleStatusView statusView = findStatusView((ViewGroup) view);
                if (statusView != null) {
                    return statusView;
                }
            }
        }
        return null;
    }

    private MultipleStatusView getStatusView(ViewGroup group) {
        if (multipleStatusView == null) {
            multipleStatusView = findStatusView(group);
            if (multipleStatusView == null) {
                throw new IllegalStateException("布局中必须包含 MultipleStatusView");
            }
        }
        return multipleStatusView;
    }

    public void showLoading(FragmentActivity activity, ViewGroup group) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        getStatusView(group).showLoading();
    }

    public void showError(FragmentActivity activity, ViewGroup group, CharSequence text) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        getStatusView(group).showError(text.toString());
    }

    public void showEmpty(FragmentActivity activity, ViewGroup group, CharSequence text) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        getStatusView(group).showEmpty(text.toString());
    }

    public void showNoNetWork(FragmentActivity activity, ViewGroup group, CharSequence text) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        getStatusView(group).showNoNetwork(text.toString());
    }

    public void showContent(FragmentActivity activity, ViewGroup group) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        getStatusView(group).showContent();
    }

}
