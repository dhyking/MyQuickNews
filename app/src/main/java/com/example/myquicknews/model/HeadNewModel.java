package com.example.myquicknews.model;

import java.util.List;

/**
 * Created by Administrator on 2016/11/18.
 */

public class HeadNewModel {

    /**
     * postid : PHOT23HBI000100A
     * hasCover : false
     * hasHead : 1
     * replyCount : 23745
     * hasImg : 1
     * digest :
     * hasIcon : false
     * docid : 9IG74V5H00963VRO_C650BARBbjlishidaiupdateDoc
     * title : 郑州高校图书馆开馆 滑梯玻璃栈道吸睛
     * order : 1
     * priority : 355
     * lmodify : 2016-11-18 12:49:31
     * boardid : photoview_bbs
     * ads : [{"title":"航拍太白山72道弯 山路蜿蜒似\u201c长龙\u201d","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/04aa359d80954a2b9310f525eb379ff220161118134024.jpeg","subtitle":"","url":"00AP0001|2213390"},{"title":"高校现混搭建筑 \"白宫\"\"天安门\"各一面","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/6e5f5df653804c65b4ded0c8b163c3bc20161118081046.jpeg","subtitle":"","url":"00AP0001|2213268"},{"title":"2016连州国际摄影年展 网易看客参展","tag":"special","imgsrc":"http://cms-bucket.nosdn.127.net/01463135af184c838daa97a5acdbc18920161118130458.jpeg","subtitle":"","url":"S1479041908317"},{"title":"访八宝山女入殓师 曾为5000具尸体整容","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/38cd8ab7ee7149e0a4982c05b1f9401020161118072753.jpeg","subtitle":"","url":"00AP0001|2213250"},{"title":"广州车展:雪佛兰多款重磅新车亮相","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/43cf953fc6544140973e23f1902d609120161117214205.jpeg","subtitle":"","url":"2O3F0008|189395"}]
     * photosetID : 00AP0001|2213234
     * imgsum : 14
     * template : normal1
     * votecount : 22164
     * skipID : 00AP0001|2213234
     * alias : Top News
     * skipType : photoset
     * cid : C1348646712614
     * hasAD : 1
     * imgextra : [{"imgsrc":"http://cms-bucket.nosdn.127.net/275fb362173c4167a47a641f60a6c41a20161118080403.jpeg"},{"imgsrc":"http://cms-bucket.nosdn.127.net/a6c09e60e29a48059ade96843a1972ca20161118080403.jpeg"}]
     * source : 网易原创
     * ename : androidnews
     * tname : 头条
     * imgsrc : http://cms-bucket.nosdn.127.net/33466175e91240f88e2cd29ec32ee6ad20161118080402.jpeg
     * ptime : 2016-11-18 08:04:15
     */

    private String postid;
    private boolean hasCover;
    private int hasHead;
    private int replyCount;
    private int hasImg;
    private String digest;
    private boolean hasIcon;
    private String docid;
    private String title;
    private int order;
    private int priority;
    private String lmodify;
    private String boardid;
    private String photosetID;
    private int imgsum;
    private String template;
    private int votecount;
    private String skipID;
    private String alias;
    private String skipType;
    private String cid;
    private int hasAD;
    private String source;
    private String ename;
    private String tname;
    private String imgsrc;
    private String ptime;
    /**
     * title : 航拍太白山72道弯 山路蜿蜒似“长龙”
     * tag : photoset
     * imgsrc : http://cms-bucket.nosdn.127.net/04aa359d80954a2b9310f525eb379ff220161118134024.jpeg
     * subtitle :
     * url : 00AP0001|2213390
     */

    private List<AdsBean> ads;
    /**
     * imgsrc : http://cms-bucket.nosdn.127.net/275fb362173c4167a47a641f60a6c41a20161118080403.jpeg
     */

    private List<ImgextraBean> imgextra;

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public boolean isHasCover() {
        return hasCover;
    }

    public void setHasCover(boolean hasCover) {
        this.hasCover = hasCover;
    }

    public int getHasHead() {
        return hasHead;
    }

    public void setHasHead(int hasHead) {
        this.hasHead = hasHead;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public int getHasImg() {
        return hasImg;
    }

    public void setHasImg(int hasImg) {
        this.hasImg = hasImg;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public boolean isHasIcon() {
        return hasIcon;
    }

    public void setHasIcon(boolean hasIcon) {
        this.hasIcon = hasIcon;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getLmodify() {
        return lmodify;
    }

    public void setLmodify(String lmodify) {
        this.lmodify = lmodify;
    }

    public String getBoardid() {
        return boardid;
    }

    public void setBoardid(String boardid) {
        this.boardid = boardid;
    }

    public String getPhotosetID() {
        return photosetID;
    }

    public void setPhotosetID(String photosetID) {
        this.photosetID = photosetID;
    }

    public int getImgsum() {
        return imgsum;
    }

    public void setImgsum(int imgsum) {
        this.imgsum = imgsum;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public int getVotecount() {
        return votecount;
    }

    public void setVotecount(int votecount) {
        this.votecount = votecount;
    }

    public String getSkipID() {
        return skipID;
    }

    public void setSkipID(String skipID) {
        this.skipID = skipID;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getSkipType() {
        return skipType;
    }

    public void setSkipType(String skipType) {
        this.skipType = skipType;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public int getHasAD() {
        return hasAD;
    }

    public void setHasAD(int hasAD) {
        this.hasAD = hasAD;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public List<AdsBean> getAds() {
        return ads;
    }

    public void setAds(List<AdsBean> ads) {
        this.ads = ads;
    }

    public List<ImgextraBean> getImgextra() {
        return imgextra;
    }

    public void setImgextra(List<ImgextraBean> imgextra) {
        this.imgextra = imgextra;
    }

    public static class AdsBean {
        private String title;
        private String tag;
        private String imgsrc;
        private String subtitle;
        private String url;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class ImgextraBean {
        private String imgsrc;

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }
    }
}
