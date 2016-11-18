package com.example.myquicknews.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.myquicknews.R;

/**
 * Created by Administrator on 2016/11/4.
 */

public class LetterCityView extends View {
    private Context mContext;
    private int width;                //总长
    private int height;               //总高度
    private int singleHeight;           //每个字母的高度
    private int selectedPosition;     //选中的字母位置
    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
    public static String[] b = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#" };
    private int choose = -1;
    private Paint paint = new Paint();

    private TextView mTextDialog;

    public LetterCityView(Context context) {
        this(context,null);
    }

    public LetterCityView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
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
    }

    /**
     * 根据手势上下滑动改变移动位置
     */
    private void changePosition(int y) {
        if (selectedPosition >= b.length) {
            selectedPosition = b.length -1;
        } else if (selectedPosition <= 0) {
            selectedPosition = 0;
        }
        selectedPosition = (y / singleHeight);

        if (onTouchingLetterChangedListener != null) {
            onTouchingLetterChangedListener.onTouchingLetterChanged(b[selectedPosition], false);
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
     * dip 转 px
     * @param dpValue
     * @return
     */
    private int dip2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale +0.5f);
    }


    public void setTextView(TextView mTextDialog) {
        this.mTextDialog = mTextDialog;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        height = getHeight();
        width = getWidth();
        singleHeight = height / b.length;

        for (int i = 0; i < b.length; i++) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                paint.setColor(mContext.getResources().getColor(R.color.dark_gray,null));
//            } else {
                paint.setColor(mContext.getResources().getColor(R.color.dark_gray));
//            }
            paint.setAntiAlias(true);
            paint.setTextSize(dip2px(15));
            if (i == choose) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    paint.setColor(mContext.getResources().getColor(R.color.dark_checked,null));
//                } else {
                    paint.setColor(mContext.getResources().getColor(R.color.dark_checked));
//                }
                paint.setFakeBoldText(true);
            }
            float xPos = width / 2 - paint.measureText(b[i]) / 2;
            float yPos = singleHeight * i + singleHeight;
            canvas.drawText(b[i], xPos, yPos, paint);
            paint.reset();
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float y = event.getY();
        final int oldChoose = choose;
        final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
        final int c = (int) (y / getHeight() * b.length);

        switch (action) {
            case MotionEvent.ACTION_UP:
                setBackgroundDrawable(new ColorDrawable(0x00000000));
                choose = -1;
                invalidate();
                if (mTextDialog != null) {
                    mTextDialog.setVisibility(View.INVISIBLE);
                }
                break;

            default:
                if (oldChoose != c) {
                    if (c >= 0 && c < b.length) {
                        if (listener != null) {
                            listener.onTouchingLetterChanged(b[c],false);
                        }
                        if (mTextDialog != null) {
                            mTextDialog.setText(b[c]);
                            mTextDialog.setVisibility(View.VISIBLE);
                        }

                        choose = c;
                        invalidate();
                    }
                }

                break;
        }
        return true;
    }


    public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }

    public interface OnTouchingLetterChangedListener {
        public void onTouchingLetterChanged(String s,boolean isHideEnable);
    }
}
