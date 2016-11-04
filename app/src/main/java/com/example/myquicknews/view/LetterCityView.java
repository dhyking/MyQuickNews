package com.example.myquicknews.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/4.
 */

public class LetterCityView extends View {
    private Context mContext;
    private Paint mPaint;             //绘制字母的画笔
    private int width;                //总长
    private int height;               //总高度
    private int textHeight;           //每个字母的高度
    private List<String> dataList;    //数据源
    private int selectedPosition;     //选中的字母位置
    private OnLetterTouchListener mOnLetterTouchListener;
    private Paint.FontMetrics mFontMetrics;

    public LetterCityView(Context context) {
        super(context);
    }

    public LetterCityView(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public LetterCityView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextAlign(Paint.Align.CENTER);
        selectedPosition = 0;
        dataList = new ArrayList<>();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = oldh;
        height = oldh;
        if (!dataList.isEmpty()) {
            textHeight = (height / dataList.size());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < dataList.size(); i++) {
            if (i == selectedPosition) {
                mPaint.setColor(Color.BLUE);
            } else {
                mPaint.setColor(Color.WHITE);
            }

            mPaint.setTextSize(dip2px(15));
            mFontMetrics = mPaint.getFontMetrics();
            canvas.drawText(dataList.get(i), width / 2 , i * textHeight + textHeight / 2 + mFontMetrics.bottom, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventY = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                changePosition(eventY);
                break;
            case MotionEvent.ACTION_MOVE:
                changePosition(eventY);
                break;
            case MotionEvent.ACTION_UP:
                if (mOnLetterTouchListener != null) {
                    mOnLetterTouchListener.onTouchListener(dataList.get(selectedPosition),false);
                }
                break;

        }
        return true;
    }

    /**
     * 根据手势上下滑动改变移动位置
     */
    private void changePosition(int y) {
        selectedPosition = y / textHeight;
        if (selectedPosition >= dataList.size()) {
            selectedPosition = dataList.size() -1;
        } else if (selectedPosition <= 0) {
            selectedPosition = 0;
        }

        if (mOnLetterTouchListener != null) {
            mOnLetterTouchListener.onTouchListener(dataList.get(selectedPosition), false);
        }
        invalidate();
    }


    /**
     * 设置选中的位置
     * @param position
     */
    public void setSelectedPosition(int position) {
        selectedPosition = position;
        invalidate();
    }

    /**
     * 设置数据源
     * @param list
     */
    public void setData(List<String> list) {
        this.dataList = list;
    }


    public interface OnLetterTouchListener {
        void onTouchListener(String str , boolean hideEnable);
    }

    public void setOnLetterTouchListener(OnLetterTouchListener onLetterTouchListener) {
        this.mOnLetterTouchListener = onLetterTouchListener;
    }

    /**
     * dip 转 px
     * @param dpValue
     * @return
     */
    private int dip2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale +0.5f);
    }
}
