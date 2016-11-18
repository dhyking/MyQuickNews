package com.example.myquicknews.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.myquicknews.model.ChannelItem;

import java.util.List;

/**
 * Created by Administrator on 2016/11/4.
 */

public class TabFragmentAdapter extends FragmentPagerAdapter {
    private String[] titles;
    private List<Fragment> mFragmentList;
    private Context mContext;
    private List<ChannelItem> showList;

    public TabFragmentAdapter(FragmentManager fm, Context mContext, List<Fragment> mFragmentList, List<ChannelItem> mChannelItemList) {
        super(fm);
        this.mContext = mContext;
        this.mFragmentList = mFragmentList;
        this.showList = mChannelItemList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
//        return (mFragmentList != null ?mFragmentList.size():0);
        return showList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return showList.get(position).getName();
    }
}
