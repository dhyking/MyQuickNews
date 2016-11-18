package com.example.myquicknews.http;

import com.example.myquicknews.activity.BaseActivity;
import com.example.myquicknews.util.MyToast;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/11/17.
 */

public abstract class BaseSubscriber<T> extends Subscriber<T>{
    private BaseActivity activity;
    private boolean isShowLoading;

    public BaseSubscriber(BaseActivity mActivity,boolean mIsShowLoading) {
        this.activity = mActivity;
        this.isShowLoading = mIsShowLoading;
    }


    @Override
    public void onStart() {
        super.onStart();
        //判断网络是否可用
        if (!HttpManager.getInstance().isNetConnected(activity)) {
            MyToast.getInstance().showTosat(activity,"网络异常,请检查",2000);
            return;
        }

        //显示进度条
        if (isShowLoading) {
            activity.showLoading();
        }
    }

    @Override
    public void onCompleted() {
        //关闭进度条
        if (isShowLoading) {
            activity.closeLoading();
        }
    }
}
