package com.starry.skindemo;

import android.app.Application;

import com.starry.skindemo.utils.SPUtils;

import skin.support.SkinCompatManager;
import skin.support.design.SkinMaterialManager;

/**
 * Created by wangsen on 2018/12/4.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SkinMaterialManager.init(this);
        SkinCompatManager.init(this).loadSkin();
        SPUtils.init(this);
    }
}
