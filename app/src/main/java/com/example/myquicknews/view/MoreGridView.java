package com.example.myquicknews.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Administrator on 2016/11/15.
 */

public class MoreGridView extends GridView {

    public MoreGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //动态测量视图高度,并设置
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandHeightSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >>2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandHeightSpec);
    }
}
