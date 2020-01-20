package com.example.basecoder.Widget.StatusView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.OnCompositionLoadedListener;
import com.example.basecoder.BaseUtils.RandomUtils;
import com.example.basecoder.R;

/**
 * Create BY Luck-S ON 14:11
 * Email: fine9987@163.com
 * Package:com.example.basecommon.View
 * Description:
 */
public class NoDataView extends RelativeLayout {
    private LottieAnimationView no_data_view;

    public NoDataView(Context context) {
        super(context);
    }

    public NoDataView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.no_data_view, this);
        no_data_view = findViewById(R.id.no_data_view);
        LottieComposition.Factory.fromAssetFileName(context,
                RandomUtils.genNumIncludeMin(1, 3) == 1 ? "no_data1.json" : "no_data2.json", new OnCompositionLoadedListener() {
                    @Override
                    public void onCompositionLoaded(@Nullable LottieComposition lottieComposition) {
                        //这句话是关键，相当于将空间和json绑定
                        no_data_view.setComposition(lottieComposition);
                    }
                });
        no_data_view.loop(true);
        startLoading();
    }

    public NoDataView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void startLoading() {
        no_data_view.playAnimation();
    }

    private void stopLoading() {
        no_data_view.cancelAnimation();
    }

    public void loading(boolean b) {
        if (b) {
            startLoading();
        } else {
            stopLoading();
        }
    }
}
