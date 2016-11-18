package com.example.myquicknews.widget.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 自定义RecyclerView的分割线
 * Created by Administrator on 2016/11/9.
 */

public class CityItemDecoration extends RecyclerView.ItemDecoration {
    private Context mContext;
    private Drawable mDivider;
    private int mOrientation;
    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
    private final int[] ATRRS = new int[] {
            android.R.attr.listDivider
    };
    public CityItemDecoration(Context mContext, int mOrientation) {
        this.mContext = mContext;
        final TypedArray ta = mContext.obtainStyledAttributes(ATRRS);
        this.mDivider = ta.getDrawable(0);
        ta.recycle();
        setOrientation(mOrientation);

    }

    //设置屏幕的方向
    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (mOrientation == HORIZONTAL_LIST) {
            drawHorizonLine(c, parent, state);
        } else {
            drawVerticalLine(c, parent, state);
        }
    }

    /**
     * 画竖线
     *
     * @param mC
     * @param mParent
     * @param mState
     */
    private void drawVerticalLine(Canvas mC, RecyclerView mParent, RecyclerView.State mState) {
        int top = mParent.getTop();
        int bottom = mParent.getHeight() - mParent.getPaddingBottom();
        final int childCount = mParent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = mParent.getChildAt(i);

            //获得child的布局信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicWidth();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(mC);
        }


    }

    /**
     * 画横线
     *
     * @param mC
     * @param mParent
     * @param mState
     */
    private void drawHorizonLine(Canvas mC, RecyclerView mParent, RecyclerView.State mState) {

        int left = mParent.getPaddingLeft();
        int right = mParent.getWidth() - mParent.getPaddingRight();
        final int childCount = mParent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = mParent.getChildAt(i);

            //获得child的布局信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(mC);
        }
    }

    //由于Divider也有长宽高，每一个Item需要向下或者向右偏移
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if(mOrientation == HORIZONTAL_LIST){
            //画横线，就是往下偏移一个分割线的高度
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        }else {
            //画竖线，就是往右偏移一个分割线的宽度
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        }
    }
}
