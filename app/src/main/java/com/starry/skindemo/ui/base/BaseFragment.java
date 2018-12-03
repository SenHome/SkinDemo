package com.starry.skindemo.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wangsen on 2018/12/3.
 */

public abstract class BaseFragment<VDB extends ViewDataBinding> extends Fragment {

    protected VDB mDataBinding;

    @LayoutRes
    protected abstract int getLayoutId();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(),null);
        mDataBinding = DataBindingUtil.bind(view);
        onCreateView(inflater,savedInstanceState);

        return view;


    }

    protected abstract void loadData();

    protected void onCreateView(LayoutInflater inflater,Bundle saveInstanceState){

    }

}
