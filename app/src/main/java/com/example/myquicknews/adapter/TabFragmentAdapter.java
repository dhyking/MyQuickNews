package com.example.myquicknews.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/11/4.
 */

public class TabFragmentAdapter extends FragmentPagerAdapter {
    private String[] titles;
    private List<Fragment> mFragmentList;
    private Context mContext;

    public TabFragmentAdapter(FragmentManager fm, Context mContext, List<Fragment> mFragmentList, String[] mTitles) {
        super(fm);
        this.mContext = mContext;
        this.mFragmentList = mFragmentList;
        this.titles = mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
//        return (mFragmentList != null ?mFragmentList.size():0);
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
