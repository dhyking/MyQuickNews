package com.example.myquicknews.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/18.
 */

public class NewsModel implements Serializable {
    private String postid;
    private String digest;
    private String docid;
    private String title;
    private String cid;
    private String url;
    private String source;
    private String imgsrc;
    private String ptime;
    private String TAG;
    private ImageModel imageModel;

    public String getCid() {
        return cid;
    }

    public void setCid(String mCid) {
        cid = mCid;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String mDigest) {
        digest = mDigest;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String mDocid) {
        docid = mDocid;
    }

    public ImageModel getImageModel() {
        return imageModel;
    }

    public void setImageModel(ImageModel mImageModel) {
        imageModel = mImageModel;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String mImgsrc) {
        imgsrc = mImgsrc;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String mPostid) {
        postid = mPostid;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String mPtime) {
        ptime = mPtime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String mSource) {
        source = mSource;
    }

    public String getTAG() {
        return TAG;
    }

    public void setTAG(String mTAG) {
        TAG = mTAG;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String mTitle) {
        title = mTitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String mUrl) {
        url = mUrl;
    }
}
