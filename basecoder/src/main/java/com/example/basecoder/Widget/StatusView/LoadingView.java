package com.example.basecoder.Widget.StatusView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.OnCompositionLoadedListener;
import com.example.basecoder.R;


/**
 * Description: TODO
 * author: Luck-s
 * date: 2019/11/26
 * version: V1.0
 */
public class LoadingView extends RelativeLayout {

    private LottieAnimationView imgLoading;
    private String filename = null;

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.view_init_loading, this);
        imgLoading = findViewById(R.id.img_init_loading);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingView);
        int integer = typedArray.getInteger(R.styleable.LoadingView_AnimationType, 1);
        switch (integer) {
            case 1:
                filename = "loading.json";
                break;
            case 2:
                filename = "loading2.json";
                break;
            case 3:
                filename = "loading3.json";
                break;
            case 4:
                filename = "load_page_anim.json";
                break;
            case 5:
                filename = "load_round.json";
                break;
            case 6:
                filename = "loading_dinosaur.json";
                break;
        }
        LottieComposition.Factory.fromAssetFileName(context, filename, new OnCompositionLoadedListener() {
            @Override
            public void onCompositionLoaded(@Nullable LottieComposition lottieComposition) {
                imgLoading.setComposition(lottieComposition);
            }
        });
        imgLoading.loop(true);
        typedArray.recycle();
        startLoading();
    }

    private void startLoading() {
        imgLoading.playAnimation();
    }

    private void stopLoading() {
        imgLoading.cancelAnimation();
    }

    public void loading(boolean b) {
        if (b) {
            startLoading();
        } else {
            stopLoading();
        }
    }
}
