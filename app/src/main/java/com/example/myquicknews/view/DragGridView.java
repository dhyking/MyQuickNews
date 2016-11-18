package com.example.myquicknews.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myquicknews.R;
import com.example.myquicknews.adapter.DragAdapter;
import com.example.myquicknews.util.DensityUtils;

/**
 * Created by Administrator on 2016/11/15.
 */

public class DragGridView extends GridView {
    private int downX;  //点击时候x位置
    private int downY;  //点击时候y的位置
    private int windowX;    //点击时候对应界面的x位置
    private int windowY;    //点击售后对应界面的y的位置
    private int win_view_x; //屏幕上x
    private int win_view_y; //屏幕上y
    private int dragOffsetX;    //拖动离X的距离
    private int dragOffsetY;    //拖动离Y的距离
    private int dragPosition;      //长按时对应的position
    private int dropPosition;   //松手后对应item的位置
    private int startPosition;  //开始拖动对应的item的位置
    private int itemHeight; //item高
    private int itemWidth;  //item宽
    private View dragImageView = null;   //拖动时item对应的view
    private View dragView = null;   //长按时item对应的view
    private WindowManager mWindowManager = null;    //windowManager管理器
    private WindowManager.LayoutParams windowParams;
    private int itemCount;  //item总量
    private int oneRowCount = 4;   //每行item数量
    private int rows;   //行数
    private int remainder;  //剩余部分
    private boolean isMoving = false;   //是否在移动
    private int holdPosition;
    private double dragScale = 1.2D;   //拖动时放大倍数
    private Vibrator mVibrator; //震动器
    private int horisonSpacing = 15; //item水平距离
    private int verticalSpacing = 15;    //item竖直距离
    private String lastAnimationId;    //移动时候最后动画id

    public DragGridView(Context context) {
        super(context);
        init(context);
    }

    public DragGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DragGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化
     * @param mContext
     */
    private void init(Context mContext) {
        mVibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
        horisonSpacing = DensityUtils.dip2px(mContext,horisonSpacing);
    }

    /** 在ScrollView内，所以要进行计算高度 */
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            downX = (int) ev.getX();
            downY = (int) ev.getY();
            windowX = (int) ev.getX();
            windowY = (int) ev.getY();
            setOnItemClickListener(ev);
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (dragImageView != null && dragPosition != AdapterView.INVALID_POSITION) {
//            移动时候对应的x,y的值
            int x = (int) ev.getX();
            int y = (int) ev.getY();
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Log.d("DragGridView", "DOWN");

                    downX = (int) ev.getX();
                    downY = (int) ev.getY();
                    windowX = (int) ev.getX();
                    windowY = (int) ev.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.d("DragGridView", "DOWN");
                    onDrag(x,y,(int)ev.getRawX(),(int)ev.getRawY());
                    if (!isMoving) {
                        onMove(x,y);
                    }

                    if (pointToPosition(x,y) != AdapterView.INVALID_POSITION) {
                        break;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    Log.d("DragGridView", "DOWN");
                    onStopDrag();
                    onDrop(x,y);
                    requestDisallowInterceptTouchEvent(false);
                    break;
            }
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 停止拖动
     */
    private void onStopDrag() {
        if (dragImageView != null) {
            mWindowManager.removeView(dragImageView);
            dragImageView = null;
        }
    }

    /**
     * 松手情况
     * @param mX
     * @param mY
     */
    private void onDrop(int mX, int mY) {
        //根据拖动x,y获取拖动位置下方item对应的position
        int tempPosition = pointToPosition(mX, mY);
        dropPosition = tempPosition;
        DragAdapter mDragAdapter = (DragAdapter) getAdapter();
        mDragAdapter.setItemShow(true); //显示刚拖动的item
        mDragAdapter.notifyDataSetChanged();

    }

    /**
     * 移动过程中
     * @param mX
     * @param mY
     */
    private void onMove(int mX, int mY) {
        //拖动view下方的position
        int dPosition = pointToPosition(mX,mY);
        //判断拖动的是不是最开始2个不能拖动的
        if (dPosition >1 ) {
            if ((dPosition == -1) || (dPosition == dragPosition)) {
                return;
            }
            dropPosition = dPosition;
            if (dragPosition != startPosition) {
                dragPosition = startPosition;
            }
            int moveCount;
            if ((dragPosition == startPosition) || (dragPosition != dropPosition)) {
                moveCount = dropPosition - dragPosition;
            } else {
                moveCount = 0;
            }
            if (moveCount ==0) {
                return;
            }
            int moveCountAbs = Math.abs(moveCount);
            if (dPosition != dragPosition) {
                ViewGroup dragGroup = (ViewGroup) getChildAt(dragPosition);
                dragGroup.setVisibility(View.INVISIBLE);
                float to_x = 1;     //当前下方position
                float to_y;         //当前下方右边position
                float x_value = ((float)horisonSpacing  / (float)itemWidth) +1.0f;
                float y_value = ((float)verticalSpacing / (float)itemHeight) + 1.0f;

                for (int i = 0; i < moveCountAbs; i++) {
                    //向左
                    if (moveCount >0) {
                        holdPosition = dragPosition + i +1;
                        if (dragPosition / oneRowCount == holdPosition /oneRowCount) {
                            to_x = x_value;
                            to_y = 0;
                        } else if (holdPosition %4 ==0 ){
                            to_x = 3 * x_value;
                            to_y = -y_value;
                        } else {
                            to_x = -x_value;
                            to_y = 0;
                        }
                    } else {
                        //向右，下移到上，右移到左
                        holdPosition = dragPosition -i -1;
                        if (dragPosition /oneRowCount == holdPosition /oneRowCount) {
                            to_x = x_value;
                            to_y = 0;
                        } else if (holdPosition /4 == 0) {
                            to_x = -3 *x_value;
                            to_y = y_value;
                        } else {
                            to_x = x_value;
                            to_y =0;
                        }
                    }

                    ViewGroup moveViewGroup = (ViewGroup) getChildAt(holdPosition);
                    Animation moveAnimation = getMoveAnimation(to_x,to_y);
                    moveViewGroup.startAnimation(moveAnimation);
                    if (holdPosition == dropPosition) {
                        lastAnimationId = moveAnimation.toString();
                    }

                    moveAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            isMoving = true;
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            // 如果为最后个动画结束，那执行下面的方法
                            if (animation.toString().equalsIgnoreCase(lastAnimationId)) {
                                DragAdapter mDragAdapter = (DragAdapter) getAdapter();
                                mDragAdapter.exchange(startPosition,dropPosition);
                                startPosition = dropPosition;
                                dragPosition = dropPosition;
                                isMoving = false;
                            }
                        }
                    });

                }
            }

        }

    }

    /** 获取移动动画 */
    private Animation getMoveAnimation(float mTo_x, float mTo_y) {
        TranslateAnimation mTranslateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0F,
                Animation.RELATIVE_TO_SELF,mTo_x,
                Animation.RELATIVE_TO_SELF, 0.0F,
                Animation.RELATIVE_TO_SELF, mTo_y);// 当前位置移动到指定位置
        mTranslateAnimation.setFillAfter(true);// 设置一个动画效果执行完毕后，View对象保留在终止的位置。
        mTranslateAnimation.setDuration(300L);
        return mTranslateAnimation;
    }

    /**
     * 拖动
     * @param mX
     * @param mY
     * @param mRawX
     * @param mRawY
     */
    private void onDrag(int mX, int mY, int mRawX, int mRawY) {
        if (dragImageView != null) {
            windowParams.alpha = 0.7f;
            windowParams.x = mRawX - win_view_x;
            windowParams.y = mRawY - win_view_y;
            mWindowManager.updateViewLayout(dragImageView,windowParams);
        }
    }


    /**
     * 长按点击监听
     * @param ev
     */
    private void setOnItemClickListener(final MotionEvent ev) {
        setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int x = (int) ev.getX();    //长按事件x位置
                int y = (int) ev.getY();    //长按事件y的位置
                startPosition = position;   //第一次点击的位置
                dragPosition = position;
                if (dragPosition <= -1) {
                    return false;
                }
                ViewGroup dragGroup = (ViewGroup) getChildAt(dragPosition - getFirstVisiblePosition());
                TextView dragTextView = (TextView) dragGroup.findViewById(R.id.tv_category_name);
                dragTextView.setEnabled(false);
                dragTextView.setSelected(true);
                itemHeight = dragGroup.getHeight();
                itemWidth = dragGroup.getWidth();
                itemCount = getCount();
                int row = itemCount / oneRowCount;
                remainder = itemCount % oneRowCount;    //最后一行多余的数量
                rows = remainder==0 ? row : row +1;     //真实的行数

                if (dragPosition != AdapterView.INVALID_POSITION) {

                    win_view_x = windowX - dragGroup.getLeft();
                    win_view_y = windowY - dragGroup.getTop();
                    dragOffsetX = (int) (ev.getRawX() - x);
                    dragOffsetY = (int) (ev.getRawY() - y);
                    dragView = dragGroup;
                    Log.d("DragGridView", "准备:"+(dragView == null)+"");
                    dragGroup.destroyDrawingCache();
                    dragGroup.setDrawingCacheEnabled(true);
                    Bitmap dragBitmap = Bitmap.createBitmap(dragGroup.getDrawingCache());
                    mVibrator.vibrate(50);
                    startDrag(dragBitmap,(int)ev.getRawX(),(int)ev.getRawY());
                    hideDragItem();
                    dragGroup.setVisibility(View.INVISIBLE);
                    isMoving = false;
                    requestDisallowInterceptTouchEvent(true);
                    return true;
                }

                return false;
            }
        });
    }

    /**
     * 隐藏拖动选项
     */
    private void hideDragItem() {
        ((DragAdapter)getAdapter()).setItemShow(false);
    }

    /**开始拖动
     * @param mDragBitmap
     * @param mRawX
     * @param mRawY
     */
    private void startDrag(Bitmap mDragBitmap, int mRawX, int mRawY) {
        onStopDrag();
        windowParams = new WindowManager.LayoutParams();
        windowParams.gravity = Gravity.TOP | Gravity.LEFT;
        windowParams.x = mRawX - win_view_x;
        windowParams.y = mRawY - win_view_y;
        windowParams.width = (int) (dragScale * mDragBitmap.getWidth());
        windowParams.height = (int) (dragScale *mDragBitmap.getHeight());
        windowParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN ;
        windowParams.format = PixelFormat.TRANSLUCENT;
        windowParams.windowAnimations = 0;
        ImageView mImageView = new ImageView(getContext());
        mImageView.setImageBitmap(mDragBitmap);
        mWindowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Log.d("DragGridView", "开始3");
        mWindowManager.addView(mImageView,windowParams);
        dragImageView = mImageView;
    }
}
