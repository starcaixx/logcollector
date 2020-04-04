package com.star.test.bean;

/**
 * @author star
 * @create 2019-03-01 20:20
 */
public class AppPraise {
    private int id;//主键id
    private int uesrid;//用户id
    private int target_id;//点赞对象id
    private int type;//点赞类型：1-问答点赞2-问答评论点赞，3-文章点赞，4-评论点赞
    private String add_time;//添加时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUesrid() {
        return uesrid;
    }

    public void setUesrid(int uesrid) {
        this.uesrid = uesrid;
    }

    public int getTarget_id() {
        return target_id;
    }

    public void setTarget_id(int target_id) {
        this.target_id = target_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }
}
