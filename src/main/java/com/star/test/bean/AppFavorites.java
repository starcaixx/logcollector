package com.star.test.bean;

/**
 * @author star
 * @create 2019-03-01 20:19
 */
public class AppFavorites {
    private int id;//主键
    private int course_id;//商品id
    private int user_id;//用户id

    private String add_time;//创建时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }
}
