package com.example.myquicknews.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.myquicknews.R;
import com.example.myquicknews.widget.DragLayout;

/**
 * Created by Administrator on 2016/11/15.
 */

public class DragActivity extends BaseActivity {
    private DragLayout dragLayout;
    private ImageView img;
    private FrameLayout container;
    private Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dragLayout = new DragLayout(this);
        super.setContentView(dragLayout);
        container = dragLayout.getContainer();
        img = dragLayout.getImg();

        if (bitmap != null) {
            img.setImageBitmap(bitmap);
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID,null);
        container.addView(view);
    }

    public void setContentView(View view) {
        container.addView(view);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bitmap != null) {
            bitmap.recycle();
        }
    }

    @Override
    public void onBackPressed() {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
            bitmap = null;
        }
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_activity_close_enter,
                R.anim.anim_slide_activity_close_exit);
    }
}
