package com.example.myquicknews.util;

import com.example.myquicknews.model.City;

import java.util.Comparator;

/**
 * Created by Administrator on 2016/11/8.
 */

public class PinYinComparator implements Comparator<City> {

    @Override
    public int compare(City o1, City o2) {
        if (o1.getPinyinName().equals("@") || o2.getPinyinName().equals("#")) {
            return -1;
        } else if (o1.getPinyinName().equals("@")) {
            return 1;
        } else {
            return o1.getPinyinName().compareTo(o2.getPinyinName());
        }
    }
}
