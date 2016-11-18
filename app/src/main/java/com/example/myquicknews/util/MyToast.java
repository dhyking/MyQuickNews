package com.example.myquicknews.util;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myquicknews.R;

/**
 * Created by Administrator on 2016/11/16.
 */

public class MyToast {
    private static MyToast instance = null;

    public MyToast() {
    }

    // 供外界调用的实例方法,单例
    public static synchronized MyToast getInstance() {
        if (null == instance) {
            instance = new MyToast();
        }
        return instance;
    }
    public void showTosat(Activity activity,String message,int timeLong){
        //使用布局加载器，将编写的toast_layout布局加载进来
        View view = activity.getLayoutInflater().inflate(R.layout.item_common_toast,null);
//        //获取ImageView
//        ImageView image = (ImageView) view.findViewById(R.id.);
//        //设置图片
//        image.setImageResource(R.drawable.cofe);
        //获取TextView
        TextView title = (TextView) view.findViewById(R.id.tv_show_toast);
        //设置显示的内容
        title.setText(message);
        Toast toast = new Toast(activity);
        //设置Toast要显示的位置，水平居中
        toast.setGravity(Gravity.CENTER, 0, 0);
        //设置显示时间
        toast.setDuration(timeLong);
        toast.setView(view);
        toast.show();
    }
}
