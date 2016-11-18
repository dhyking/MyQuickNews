package com.example.myquicknews.http;

import com.example.myquicknews.Constant;
import com.google.gson.JsonObject;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/16.
 */

public interface ApiService {
    public static final String host = "http://c.m.163.com/";
    public static final String endDetailUrl = "/full.html";
    // 头条
    public static final String TopUrl = host + "nc/article/headline/";

    // 新闻详情
    public static final String NewDetail = host + "nc/article/";
    // 足球
    public static final String CommonUrl = host + "nc/article/list/";


    //头条
    @GET(Constant.TopId+"/{index}"+Constant.endUrl)
    Observable<JsonObject> requestForTopHead(@Path("index") int index);

    //各新闻版块
    @GET("{id}"+"/"+"{index}"+Constant.endUrl)
    Observable<JsonObject> requestNewsData(@Path("id") String id,@Path("index") int index);





}
