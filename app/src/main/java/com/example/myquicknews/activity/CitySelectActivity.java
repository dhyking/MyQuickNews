package com.example.myquicknews.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myquicknews.HanziToPinyin;
import com.example.myquicknews.R;
import com.example.myquicknews.adapter.MyCityAdapter;
import com.example.myquicknews.view.LetterCityView;
import com.example.myquicknews.widget.city.CityData;
import com.example.myquicknews.widget.ui.CityItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CitySelectActivity extends BaseActivity {

    @BindView(R.id.recycle_city)
    RecyclerView mRecycleCity;
    @BindView(R.id.tv_head_show1)
    TextView mTvHeadShow;
    @BindView(R.id.letter_view)
    LetterCityView mLetterView;
    @BindView(R.id.tv_letter_show)
    TextView mTvLetterShow;
    @BindView(R.id.top_head)
    LinearLayout mTopHead;

    private List<String> dataList;
    private List<String> cityList;
    private boolean move;
    private int selectPosition = 0;
    private LinearLayoutManager mLinearLayoutManager;
    private String[] strArr = new String[]{"漯河", "濮阳", "重庆", "亳州", "儋州", "张掖", "衢州"};
    private MyCityAdapter mAdapter;
    private int lastFirstVisibleItem = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_select);
        ButterKnife.bind(this);
        getData();
        setListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initLeft();
    }

    private void initLeft() {
        List<String> list = new ArrayList<>();

    }

    /**
     * 汉字转换拼音，字母原样返回，都转换为小写
     *
     * @param input
     * @return
     */
    public static String getPinYin(String input) {
        ArrayList<HanziToPinyin.Token> tokens = HanziToPinyin.getInstance().get(input);
        StringBuilder sb = new StringBuilder();
        if (tokens != null && tokens.size() > 0) {
            for (HanziToPinyin.Token token : tokens) {
                if (token.type == HanziToPinyin.Token.PINYIN) {
                    sb.append(token.target);
                } else {
                    sb.append(token.source);
                }
            }
        }
        return sb.toString().toLowerCase();
    }

    /**
     * 获取数据源
     */
    private void getData() {
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecycleCity.setLayoutManager(mLinearLayoutManager);
        cityList = CityData.getInitCityData();
        mAdapter = new MyCityAdapter(cityList, this);
        mRecycleCity.setHasFixedSize(true);
        CityItemDecoration mCityItemDecoration = new CityItemDecoration(this, RecyclerView.HORIZONTAL);
        mRecycleCity.addItemDecoration(mCityItemDecoration);
        mRecycleCity.setAdapter(mAdapter);
//        mItemTouchHelper.attachToRecyclerView(mRecycleCity);

    }

    /**
     * 设置右侧字母手势滑动及列表滑动监听
     */
    private void setListener() {
        mLetterView.setTextView(mTvLetterShow);
        mLetterView.setOnTouchingLetterChangedListener(new LetterCityView.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s, boolean isHideEnable) {
                int position = mAdapter.getPositionForSelection(s.charAt(0));
                if (position != -1) {
                    mLinearLayoutManager.scrollToPositionWithOffset(position, 0);
                }
            }
        });


        mRecycleCity.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
                int selection = mAdapter.getSelectionForPosition(firstVisibleItem);
                int nextSelPosition = mAdapter.getPositionForSelection(selection + 1);

                if (firstVisibleItem != lastFirstVisibleItem) {
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mTopHead
                            .getLayoutParams();
                    params.topMargin = 0;
                    mTopHead.setLayoutParams(params);
                    mTvHeadShow.setText(String.valueOf((char) selection));
                }

                if (nextSelPosition == firstVisibleItem + 1) {
                    View view = recyclerView.getChildAt(0);
                    if (view != null) {
                        int titleHeight = mTopHead.getHeight();
                        int bottom = view.getBottom();
                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mTopHead.getLayoutParams();
                        if (bottom < titleHeight) {
                            float pushedDistance = bottom - titleHeight;
                            params.topMargin = (int) pushedDistance;
                            mTopHead.setLayoutParams(params);
                        } else {
                            if (params.topMargin != 0) {
                                params.topMargin = 0;
                                mTopHead.setLayoutParams(params);
                            }
                        }
                    }
                }
                lastFirstVisibleItem = firstVisibleItem;

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

    }

}
