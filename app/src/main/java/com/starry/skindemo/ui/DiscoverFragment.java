package com.starry.skindemo.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import com.starry.skindemo.R;
import com.starry.skindemo.databinding.FragmentDiscoverBinding;
import com.starry.skindemo.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangsen on 2018/12/3.
 */

public class DiscoverFragment extends BaseFragment<FragmentDiscoverBinding>{


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_discover;
    }

    @Override
    protected void onCreateView(LayoutInflater inflater, Bundle saveInstanceState) {
        super.onCreateView(inflater, saveInstanceState);
        configFragments();
    }

    private void configFragments() {
        List<Fragment> list = new ArrayList<>();

    }

    @Override
    protected void loadData() {

    }
}
