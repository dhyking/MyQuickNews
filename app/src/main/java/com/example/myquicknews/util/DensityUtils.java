package com.example.myquicknews.util;

import android.content.Context;

/**
 * Created by Administrator on 2016/11/15.
 */

public class DensityUtils {
    /**
     * dip转px
     * @param mContext  上下文
     * @param dipValue  布局中dip
     * @return
     */
    public static int dip2px(Context mContext, float dipValue) {
        float scale = mContext.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }

    /**
     * px转dip
     * @param mContext 上下文
     * @param pxValue   布局中px
     * @return
     */
    private static int px2dip(Context mContext,float pxValue) {
        float scale = mContext.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale +0.5f);
    }
}
