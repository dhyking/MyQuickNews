package com.example.myquicknews.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.myquicknews.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * 网络请求管理类
 * Created by Administrator on 2016/11/16.
 */

public class HttpManager {
    private static final int TIME_OUT_LIMIT = 10;   //默认超时
    private RxJavaCallAdapterFactory mAdapterFactory;
    private GsonConverterFactory mGsonConverterFactory;
    private OkHttpClient.Builder mBuilder;
    private static HttpManager instance = null;

    public HttpManager() {
        mGsonConverterFactory = GsonConverterFactory.create();
        mAdapterFactory = RxJavaCallAdapterFactory.create();
        //设置超时时间
        mBuilder = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT_LIMIT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT_LIMIT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT_LIMIT, TimeUnit.SECONDS);
    }

    public static synchronized HttpManager getInstance(){
        if (instance == null) {
            instance = new HttpManager();
        }
        return instance;
    }

    /**
     * 获取retrofit对象
     * @param url
     * @return
     */
    public Retrofit getRetrofit(String url) {
        if (BuildConfig.DEBUG ) {
            // Log信息拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //设置 Debug Log 模式
            mBuilder.addInterceptor(loggingInterceptor);
        }

        return new Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(mAdapterFactory)
                .addConverterFactory(mGsonConverterFactory)
                .client(mBuilder.build())
                .build();
    }

    public boolean isNetConnected(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null && mNetworkInfo.isAvailable() && mNetworkInfo.isConnected()) {
            return true;
        }
        return false;
    }


}
