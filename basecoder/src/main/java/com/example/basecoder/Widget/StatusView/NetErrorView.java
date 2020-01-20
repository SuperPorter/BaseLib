package com.example.basecoder.Widget.StatusView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.OnCompositionLoadedListener;
import com.example.basecoder.BaseUtils.RandomUtils;
import com.example.basecoder.R;

/**
 * Create BY Luck-S ON 8:46
 * Email: fine9987@163.com
 * Package:com.example.basecommon.View
 * Description:
 */
public class NetErrorView extends RelativeLayout {
    private LottieAnimationView lottie_neterror_view;

    public NetErrorView(@NonNull Context context) {
        super(context);
    }

    public NetErrorView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.view_net_error, this);
        lottie_neterror_view = findViewById(R.id.lottie_neterror_view);
        LottieComposition.Factory.fromAssetFileName(context,
                RandomUtils.genNumIncludeMin(1, 3) == 1 ? "404_anim.json" : "404_anim2.json", new OnCompositionLoadedListener() {
                    @Override
                    public void onCompositionLoaded(@Nullable LottieComposition lottieComposition) {
                        //这句话是关键，相当于将空间和json绑定
                        lottie_neterror_view.setComposition(lottieComposition);
                    }
                });
        lottie_neterror_view.loop(true);
        startLoading();
    }

    public NetErrorView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void startLoading() {
        lottie_neterror_view.playAnimation();
    }

    private void stopLoading() {
        lottie_neterror_view.cancelAnimation();
    }

    public void Loading(boolean isLoading) {
        if (isLoading) {
            startLoading();
        } else {
            stopLoading();
        }
    }
}
