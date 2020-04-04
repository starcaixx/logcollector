package com.star.test.bean;

/**
 * @author star
 * @create 2019-03-01 19:42
 */
public class AppLoading {

    private String action;//动作：开始加载=1，加载成功=2，加载事变=3
    private String loading_time;//加载时长，计算下拉开始到接口返回数据的时间，（开始加载报0，加载成功或失败才上报时间）
    private String loading_way;//加载类型：1-读取缓存，2-从接口拉新数据
    private String extent1;//扩展字段1
    private String extent2;//扩展字段2
    private String type;//加载类型：1-自动加载，用户下拽加载-2，底部加载-3
    private String type1;//加载失败码：把加载失败状态码抱回来（报空为加载成功）

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getLoading_time() {
        return loading_time;
    }

    public void setLoading_time(String loading_time) {
        this.loading_time = loading_time;
    }

    public String getLoading_way() {
        return loading_way;
    }

    public void setLoading_way(String loading_way) {
        this.loading_way = loading_way;
    }

    public String getExtent1() {
        return extent1;
    }

    public void setExtent1(String extent1) {
        this.extent1 = extent1;
    }

    public String getExtent2() {
        return extent2;
    }

    public void setExtent2(String extent2) {
        this.extent2 = extent2;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }
}
