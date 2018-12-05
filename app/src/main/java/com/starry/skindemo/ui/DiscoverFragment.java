package com.starry.skindemo.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import com.starry.skindemo.R;
import com.starry.skindemo.databinding.FragmentDiscoverBinding;
import com.starry.skindemo.ui.adapter.TabFragmentPagerAdapter;
import com.starry.skindemo.ui.base.BaseFragment;
import com.starry.skindemo.ui.discover.RadioFragment;
import com.starry.skindemo.ui.discover.RankingFragment;
import com.starry.skindemo.ui.discover.RecommendFragment;
import com.starry.skindemo.ui.discover.SongMenuFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangsen on 2018/12/3.
 */

public class DiscoverFragment extends BaseFragment<FragmentDiscoverBinding>{

    private TabFragmentPagerAdapter mTabFragmentPagerAdapter;

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
        list.add(new RecommendFragment());
        list.add(new SongMenuFragment());
        list.add(new RadioFragment());
        list.add(new RankingFragment());

        List<String> listTitle = new ArrayList<>();
        listTitle.add("个性化推荐");
        listTitle.add("歌单");
        listTitle.add("主播电台");
        listTitle.add("排行榜");

        mTabFragmentPagerAdapter = new TabFragmentPagerAdapter(getChildFragmentManager(),list,listTitle);
        mDataBinding.viewPager.setAdapter(mTabFragmentPagerAdapter);
        mDataBinding.tabLayout.setupWithViewPager(mDataBinding.viewPager);



    }

    @Override
    protected void loadData() {

    }
}
