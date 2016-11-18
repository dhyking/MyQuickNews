package com.example.myquicknews.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/4.
 */

public class City implements Serializable{
    //拼音首字母
    private String firstLetters;

    //隐藏，展开字母列表项
    private boolean hideEnable;
    private String pinyinName;
    private String fullName;

    public boolean isHideEnable() {
        return hideEnable;
    }

    public void setHideEnable(boolean mHideEnable) {
        hideEnable = mHideEnable;
    }

    public String getFirstLetters() {
        return firstLetters;
    }

    public void setFirstLetters(String mFirstLetters) {
        firstLetters = mFirstLetters;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String mFullName) {
        fullName = mFullName;
    }

    public String getPinyinName() {
        return pinyinName;
    }

    public void setPinyinName(String mPinyinName) {
        pinyinName = mPinyinName;
    }
}
