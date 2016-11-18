package com.example.myquicknews.adapter;

import android.content.Context;

import com.example.myquicknews.R;
import com.example.myquicknews.model.NewsModel;
import com.example.myrecyclerview.BaseRecyclerAdapter;
import com.example.myrecyclerview.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/11/18.
 */

public class NewsAdapter extends BaseRecyclerAdapter<NewsModel> {
    public NewsAdapter(Context context, List<NewsModel> data, int layoutResId) {
        super(context, data, layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsModel item) {
        helper.setTag(R.id.tv_title,item.getTitle());

    }

}
