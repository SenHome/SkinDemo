package com.starry.skindemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.starry.skindemo.ui.base.BaseActivity;

/**
 * Created by wangsen on 2018/12/3.
 */

public class SplashActivity extends BaseActivity {
    @Override
    protected int getLaoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                overridePendingTransition(R.anim.screen_zoom_in,R.anim.screen_zoom_out);
            }
        });
    }
}
