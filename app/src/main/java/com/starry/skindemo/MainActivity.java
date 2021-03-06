package com.starry.skindemo;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.starry.skindemo.databinding.ActivityMainBinding;
import com.starry.skindemo.databinding.MainHeaderLayoutBinding;
import com.starry.skindemo.ui.DiscoverFragment;
import com.starry.skindemo.ui.FriendsFragment;
import com.starry.skindemo.ui.MusicFragment;
import com.starry.skindemo.ui.SkinLibActivity;
import com.starry.skindemo.ui.adapter.TabFragmentPagerAdapter;
import com.starry.skindemo.ui.base.BaseActivity;
import com.starry.skindemo.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import skin.support.SkinCompatManager;

import static com.starry.skindemo.DataoManager.NIGHT_SKIN;
import static com.starry.skindemo.DataoManager.SKIN_LIBS;
import static com.starry.skindemo.DataoManager.SKIN_NAMES;

public class MainActivity extends BaseActivity<ActivityMainBinding>{

    private final static String TAG = MainActivity.class.getSimpleName();
    private static final int SELECT_SKIN_REQUEST_CODE = 100;

    private ViewPagerListener mViewPagerListener = new ViewPagerListener();
    private int mCurrentFragment = TabState.DEFAULT;

    private MainHeaderLayoutBinding mMainHeaderLayoutBinding;


    @Override
    protected int getLaoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding.setListener(this);
        initToolbar(mDataBinding.toolBar);
        initNavigationView(mDataBinding.navigationView);
        configFragments();

    }

    private void configFragments() {
        List<Fragment> list = new ArrayList<>();
        list.add(new DiscoverFragment());
        list.add(new MusicFragment());
        list.add(new FriendsFragment());
        mDataBinding.viewPager.setAdapter(new TabFragmentPagerAdapter(getSupportFragmentManager(),list));
        mDataBinding.viewPager.addOnPageChangeListener(mViewPagerListener);
        setPageSelected(mCurrentFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_options,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_search:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void goDiscoverTab(View view){
        setPageSelected(TabState.DEFAULT);
    }
    public void goMusicTab(View view){
        setPageSelected(TabState.MUSIC);
    }
    public void goFriendsTab(View view){
        setPageSelected(TabState.FRIENDS);
    }

    public boolean getNightMode(){
        return SPUtils.getInstance().getNightMode();
    }

    public void goAboutSkinLibActivity(View view){
//        Intent intent = new Intent(this)
    }

    public void goSkinLibActivity(View view){
        mDataBinding.drawLayout.closeDrawer(GravityCompat.START);
        Intent intent = new Intent(this, SkinLibActivity.class);
        startActivityForResult(intent,SELECT_SKIN_REQUEST_CODE);

    }

    public void onNightModeClick(View view){
        if(!SPUtils.getInstance().getNightMode()){
            SPUtils.getInstance().setCurSkin(SkinCompatManager.getInstance().getCurSkinName()).commitEditor();
            SkinCompatManager.getInstance().loadSkin(NIGHT_SKIN);

        }else {
            SkinCompatManager.getInstance().loadSkin(SPUtils.getInstance().getCurSkin());
        }

        SPUtils.getInstance().setNightMode(!SPUtils.getInstance().getNightMode()).commitEditor();
        mMainHeaderLayoutBinding.dayNightSwitch.setChecked(SPUtils.getInstance().getNightMode());
    }


    private void initNavigationView(NavigationView navigationView) {

        View headerView = getLayoutInflater().inflate(R.layout.main_header_layout, null, false);
        navigationView.addHeaderView(headerView);
        mMainHeaderLayoutBinding = DataBindingUtil.bind(headerView);
        mMainHeaderLayoutBinding.setListener(this);
        initSkinName();
    }

    private void initSkinName() {
        String curSkin = SkinCompatManager.getInstance().getCurSkinName();
        String curSkinName = SKIN_NAMES[0];
        for (int i = 0; i < SKIN_LIBS.length; i++) {
            if(SKIN_LIBS[i].equals(curSkin)){
                curSkinName = SKIN_NAMES[i];
                break;
            }
        }
        mMainHeaderLayoutBinding.curSkinName.setText(curSkinName);
        mMainHeaderLayoutBinding.dayNightSwitch.setChecked(SPUtils.getInstance().getNightMode());

    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDataBinding.drawLayout.openDrawer(GravityCompat.START);
            }
        });
    }


    public void setPageSelected(int position){
        mCurrentFragment = position;
        if(position != mDataBinding.viewPager.getCurrentItem()){
            mDataBinding.viewPager.setCurrentItem(position);
        }

        mDataBinding.discover.setSelected(false);
        mDataBinding.music.setSelected(false);
        mDataBinding.friends.setSelected(false);

        switch (position){
            case TabState.DEFAULT:
                mDataBinding.discover.setSelected(true);
                break;
            case TabState.MUSIC:
                mDataBinding.music.setSelected(true);
                break;
            case TabState.FRIENDS:
                mDataBinding.friends.setSelected(true);
                break;
        }
    }

    public class ViewPagerListener extends ViewPager.SimpleOnPageChangeListener{
        @Override
        public void onPageSelected(int position) {
            setPageSelected(position);
        }

    }

    public interface TabState {
        //默认，音乐，朋友三个Tab
        int DEFAULT = 0;
        int MUSIC = 1;
        int FRIENDS = 2;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (SELECT_SKIN_REQUEST_CODE == requestCode) {
            initSkinName();
        }
    }

    @Override
    public void onBackPressed() {
        if(mDataBinding.drawLayout.isDrawerOpen(GravityCompat.START)){
            mDataBinding.drawLayout.closeDrawer(GravityCompat.START);
            return;
        }
        super.onBackPressed();
    }
}
