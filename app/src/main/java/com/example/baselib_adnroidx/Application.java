package com.example.baselib_adnroidx;

import com.example.basecoder.Base.BaseApplication;
import com.example.basecoder.Image.ImageLoader;
import com.example.basehttp.HttpUtils;

/**
 * Create BY Luck-S ON 14:22
 * Email: fine9987@163.com
 * Package:com.example.baselib_adnroidx
 * Description:
 */
public class Application extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoader.init(this);
        HttpUtils.getInstance().init(this);
    }
}
