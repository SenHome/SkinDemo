package com.starry.skindemo.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.starry.skindemo.R;

import skin.support.app.SkinCompatActivity;

/**
 * Created by wangsen on 2018/12/3.
 */

public abstract class BaseActivity<VDB extends ViewDataBinding> extends SkinCompatActivity {

    protected VDB mDataBinding;

    @LayoutRes
    protected abstract int getLaoutResId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this,getLaoutResId());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void initToolbar(Toolbar toolbar){
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
