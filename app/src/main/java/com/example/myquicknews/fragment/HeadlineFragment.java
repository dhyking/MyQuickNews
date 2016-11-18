package com.example.myquicknews.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myquicknews.Constant;
import com.example.myquicknews.MainActivity;
import com.example.myquicknews.R;
import com.example.myquicknews.adapter.NewsAdapter;
import com.example.myquicknews.http.ApiService;
import com.example.myquicknews.http.BaseSubscriber;
import com.example.myquicknews.http.HttpManager;
import com.example.myquicknews.http.json.NewsJson;
import com.example.myquicknews.model.NewsModel;
import com.example.myquicknews.widget.ui.CityItemDecoration;
import com.example.myrecyclerview.listener.OnRecyclerItemClickListener;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 头条界面
 */
public class HeadlineFragment extends BaseFragment {

    private String mText;
    private View view;
    private List<NewsModel> headList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_headline, container, false);
        }
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_head);

        initData();


        return view;
    }

    /**
     * 初始化新闻数据
     */
    private void initData() {
        requestHeadline();
    }

    /**
     * 请求头条消息
     */
    private void requestHeadline() {
        HttpManager mHttpManager = HttpManager.getInstance();
        ApiService mApiService = mHttpManager.getRetrofit(ApiService.TopUrl).create(ApiService.class);
        Observable<JsonObject> mObservable = mApiService.requestForTopHead(0);
        mObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<JsonObject>((MainActivity) getActivity(), false) {

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(JsonObject mJsonObject) {
                        Log.d("HeadlineFragment", "JSON:" + mJsonObject.toString());
                        Log.d("HeadlineFragment", "获取数据");
                        String result = mJsonObject.toString();
                        headList = NewsJson.getInstance(getActivity()).
                                getNewsModel(result, Constant.TopId);
                        setRefreshAndMore(headList);
                    }
                });
    }

    private void setRefreshAndMore(List<NewsModel> mHeadList) {
        NewsAdapter mAdapter = new NewsAdapter(getActivity(),mHeadList,R.layout.item_frag_news);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new CityItemDecoration(getActivity(),CityItemDecoration.HORIZONTAL_LIST));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                NewsModel model = (NewsModel) data;
                Log.d("HeadlineFragment", "--------------"+model.getImgsrc());
            }
        });
    }


}
