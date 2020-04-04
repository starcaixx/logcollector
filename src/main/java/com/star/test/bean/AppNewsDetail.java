package com.star.test.bean;

/**
 * @author star
 * @create 2019-03-01 19:32
 */
public class AppNewsDetail {
    private String entry;//页面入口来源：应用首页=1，push=2，详情相关推荐=3
    private String action;//动作：开始加载=1，加载成功=2（pv），加载失败=3，退出页面=4
    private String newsid;//商品ID，（服务器端下发的ID）
    private String showtype;//商品样式：0，无图，1-一张大图，2-两张图，3-三张小图，4-一张小图，5-一张大图两张小图
    private String news_staytime;//页面停留时长:从商品开始加载到开始计算，到用户关闭页面所用的时间。
    private String loading_time;//加载时长：计算页面开始加载到接口返回数据的时间
    private String type1;//加载失败码，将加载失败的状态吗饭回来，报空为加载成功
    private String category;//分类ID（服务器端定义的ID）

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getNewsid() {
        return newsid;
    }

    public void setNewsid(String newsid) {
        this.newsid = newsid;
    }

    public String getShowtype() {
        return showtype;
    }

    public void setShowtype(String showtype) {
        this.showtype = showtype;
    }

    public String getNews_staytime() {
        return news_staytime;
    }

    public void setNews_staytime(String news_staytime) {
        this.news_staytime = news_staytime;
    }

    public String getLoading_time() {
        return loading_time;
    }

    public void setLoading_time(String loading_time) {
        this.loading_time = loading_time;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
