package com.example.myquicknews.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.myquicknews.R;
import com.example.myquicknews.model.City;
import com.example.myquicknews.view.LetterCityView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CitySelectActivity extends AppCompatActivity {

    @BindView(R.id.recycle_city)
    RecyclerView mRecycleCity;
    @BindView(R.id.tv_head_show)
    TextView mTvHeadShow;
    @BindView(R.id.letter_view)
    LetterCityView mLetterView;
    @BindView(R.id.tv_letter_show)
    TextView mTvLetterShow;

    private List<String> dataList;
    private List<City> cityList;
    private boolean move;
    private int selectPosition =0;
    private LetterCityView mLetterCityView;
    public static final int HEAD_FIRST_VIEW = 0x00000111;
    public static final int HEAD_VISABLE_VIEW = 0x00000222;
    public static final int HEAD_NONE_VIEW = 0x00000333;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_select);
        ButterKnife.bind(this);
        getData();

    }

    /**
     * 获取数据源
     */
    private void getData() {
        dataList = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            dataList.add(getCityData());
        }
    }

    private String getCityData() {
        return null;
    }
}
