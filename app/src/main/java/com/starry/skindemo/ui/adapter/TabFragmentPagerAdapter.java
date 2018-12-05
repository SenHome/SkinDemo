package com.starry.skindemo.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by wangsen on 2018/12/4.
 */

public class TabFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragment;
    private List<String> mTitles;

    public TabFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragment) {
        super(fm);
        mFragment = fragment;
    }

    public TabFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragment, List<String> titles) {
        super(fm);
        mFragment = fragment;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles != null ? mTitles.get(position) : "";
    }
}
