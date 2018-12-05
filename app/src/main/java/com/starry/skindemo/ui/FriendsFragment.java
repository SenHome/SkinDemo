package com.starry.skindemo.ui;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import com.starry.skindemo.R;
import com.starry.skindemo.databinding.FragmentFriendsBinding;
import com.starry.skindemo.ui.adapter.TabFragmentPagerAdapter;
import com.starry.skindemo.ui.base.BaseFragment;
import com.starry.skindemo.ui.friends.ContactsFragment;
import com.starry.skindemo.ui.friends.FeedsFragment;
import com.starry.skindemo.ui.friends.NearbyFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangsen on 2018/12/3.
 */

public class FriendsFragment extends BaseFragment<FragmentFriendsBinding> {
    private TabFragmentPagerAdapter mTabFragmentPagerAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_friends;
    }

    @Override
    protected void onCreateView(LayoutInflater inflater, Bundle saveInstanceState) {
        super.onCreateView(inflater, saveInstanceState);
        configFragment();
    }

    private void configFragment() {
        List<Fragment> list = new ArrayList<>();
        list.add(new FeedsFragment());
        list.add(new NearbyFragment());
        list.add(new ContactsFragment());

        List<String> listTitle = new ArrayList<>();
        listTitle.add("动态");
        listTitle.add("附近");
        listTitle.add("好友");

        mTabFragmentPagerAdapter = new TabFragmentPagerAdapter(getChildFragmentManager(),list,listTitle);
        mDataBinding.viewPager.setAdapter(mTabFragmentPagerAdapter);
        mDataBinding.tabLayout.setupWithViewPager(mDataBinding.viewPager);
    }


    @Override
    protected void loadData() {

    }
}
