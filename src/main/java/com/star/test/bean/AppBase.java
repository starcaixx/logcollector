package com.star.test.bean;

import java.io.Serializable;

/**
 * @author star
 * @create 2019-03-01 19:12
 */
public class AppBase implements Serializable {

    private String mid;//设备唯一标识
    private String uid;//用户uid
    private String vc;//程序版本号
    private String vn;//程序版本名
    private String language;//系统语言
    private String sr;//渠道号，应用从哪个渠道来
    private String os;//android 版本
    private String ar;//区域
    private String md;//手机型号
    private String ba;//手机品牌
    private String sv;//sdkVersion
    private String gmail;//gmail
    private String hw;//heightwidth 屏幕宽高
    private String time;//日志产生时间
    private String nw;//网络模式

    private String ln;//经度
    private String la;//维度

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getVc() {
        return vc;
    }

    public void setVc(String vc) {
        this.vc = vc;
    }

    public String getVn() {
        return vn;
    }

    public void setVn(String vn) {
        this.vn = vn;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSr() {
        return sr;
    }

    public void setSr(String sr) {
        this.sr = sr;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getAr() {
        return ar;
    }

    public void setAr(String ar) {
        this.ar = ar;
    }

    public String getMd() {
        return md;
    }

    public void setMd(String md) {
        this.md = md;
    }

    public String getBa() {
        return ba;
    }

    public void setBa(String ba) {
        this.ba = ba;
    }

    public String getSv() {
        return sv;
    }

    public void setSv(String sv) {
        this.sv = sv;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getHw() {
        return hw;
    }

    public void setHw(String hw) {
        this.hw = hw;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNw() {
        return nw;
    }

    public void setNw(String nw) {
        this.nw = nw;
    }

    public String getLn() {
        return ln;
    }

    public void setLn(String ln) {
        this.ln = ln;
    }

    public String getLa() {
        return la;
    }

    public void setLa(String la) {
        this.la = la;
    }
}
