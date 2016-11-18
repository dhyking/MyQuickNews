package com.example.myquicknews.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/11/18.
 */

public class ImageModel implements Serializable{
    private String title;
    private List<String> list;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> mList) {
        list = mList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String mTitle) {
        title = mTitle;
    }
}