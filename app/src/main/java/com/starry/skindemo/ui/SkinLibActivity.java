package com.starry.skindemo.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.view.Menu;
import android.view.View;

import com.starry.skindemo.R;
import com.starry.skindemo.databinding.ActivitySkinLibBinding;
import com.starry.skindemo.databinding.HeaderSkinLibBinding;
import com.starry.skindemo.ui.adapter.SkinLibAdapter;
import com.starry.skindemo.ui.base.BaseActivity;
import com.starry.skindemo.utils.SPUtils;

import skin.support.SkinCompatManager;

import static com.starry.skindemo.DataoManager.SKIN_LIBS;

/**
 * Created by wangsen on 2018/12/5.
 */

public class SkinLibActivity extends BaseActivity<ActivitySkinLibBinding> {

    private HeaderSkinLibBinding mHeaderBinding;
    private SkinLibAdapter mAdapter;
    private int mCurSkin = -1;

    @Override
    protected int getLaoutResId() {
        return R.layout.activity_skin_lib;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar(mDataBinding.toolBar);
        mHeaderBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.header_skin_lib,null,false);
        initRecyclerView();

        initHeaderView();
    }

    private void initHeaderView() {
        mHeaderBinding.red.name.setText("官方红");
        mHeaderBinding.red.preview.setAspectRatio(4 * 1.0f / 3);
        mHeaderBinding.red.preview.setImageResource(R.drawable.skin_red);
        mHeaderBinding.red.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SkinCompatManager.getInstance().restoreDefaultTheme();
                SPUtils.getInstance().setNightMode(false).commitEditor();
                showSkinIndicator(0);
            }
        });

        mHeaderBinding.white.name.setText("官方白");
        mHeaderBinding.white.preview.setAspectRatio(4 * 1.0f /3);
        mHeaderBinding.white.preview.setImageResource(R.drawable.skin_white);
        mHeaderBinding.white.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SkinCompatManager.getInstance().loadSkin("white.skin", new SkinCompatManager.SkinLoaderListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess() {
                        SPUtils.getInstance().setNightMode(false).commitEditor();
                        showSkinIndicator();
                    }

                    @Override
                    public void onFailed(String errMsg) {

                    }
                });
            }
        });

        mHeaderBinding.color.name.setText("自选颜色");
        mHeaderBinding.color.preview.setAspectRatio(4 * 1.0f / 3);
        mHeaderBinding.color.preview.setImageResource(R.drawable.skin_color);
        showSkinIndicator();
    }

    private void showSkinIndicator(){
        String curSkinName = SkinCompatManager.getInstance().getCurSkinName();
        for (int i = 0; i < SKIN_LIBS.length; i++) {
            if(SKIN_LIBS[i].equals(curSkinName)){
                showSkinIndicator(i);
            }
        }
    }

    private void showSkinIndicator(int index) {
        hideSkinIndicator(mCurSkin);
        switch (index){
            case 0:
                mHeaderBinding.red.description.setText("使用中");
                mHeaderBinding.red.indicator.setVisibility(View.VISIBLE);
                mHeaderBinding.red.description.setVisibility(View.VISIBLE);
                mCurSkin = 0;
                break;
            case 1:
                mHeaderBinding.white.description.setText("使用中");
                mHeaderBinding.white.indicator.setVisibility(View.VISIBLE);
                mHeaderBinding.white.description.setVisibility(View.VISIBLE);
                mCurSkin = 1;
                break;
            case 2:
                mHeaderBinding.color.description.setText("使用中");
                mHeaderBinding.color.indicator.setVisibility(View.VISIBLE);
                mHeaderBinding.color.description.setVisibility(View.VISIBLE);
                mCurSkin = 2;
                break;
        }

    }

    private void hideSkinIndicator(int index) {

        mCurSkin = -1;
        switch (index){
            case 0:
                mHeaderBinding.red.indicator.setVisibility(View.INVISIBLE);
                mHeaderBinding.red.description.setVisibility(View.INVISIBLE);
                break;
            case 1:
                mHeaderBinding.white.indicator.setVisibility(View.INVISIBLE);
                mHeaderBinding.white.description.setVisibility(View.INVISIBLE);
                break;
            case 2:
                mHeaderBinding.color.indicator.setVisibility(View.INVISIBLE);
                mHeaderBinding.color.description.setVisibility(View.INVISIBLE);
                break;
        }
    }

    private void initRecyclerView() {
        mDataBinding.recyclerView.setPullRefreshEnabled(false);
        mDataBinding.recyclerView.setLoadingMoreEnabled(true);
        mDataBinding.recyclerView.addHeaderView(mHeaderBinding.getRoot());
        mDataBinding.recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        mDataBinding.recyclerView.setNestedScrollingEnabled(false);
        mDataBinding.recyclerView.setHasFixedSize(false);
        mDataBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new SkinLibAdapter();
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
