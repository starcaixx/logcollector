package com.star.test.appclient;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.star.test.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * @author star
 * @create 2019-03-01 20:30
 */
public class AppMain {
    private final static Logger logger = LoggerFactory.getLogger(AppMain.class);
    static Random rand = new Random();

    /**
     * 1,appkey
     * 2,循环遍历次数
     * 3，uid的长度
     * 4，商品id的长度，默认是4
     * @param args
     */
    public static void main(String[] args) {
        //appkey的名称
        String appkey = args.length > 0 ? args[0] : "gmall";
        //循环遍历次数
        int loop_len = args.length > 1 ? Integer.valueOf(args[1]) : 1*10;

        //mid的长度
        int mid = args.length > 2 ? Integer.valueOf(args[2]) : 3;
        //uid的长度
        int uid = args.length > 3 ? Integer.valueOf(args[3]) : 3;
        //商品id的长度
        int newsid_length = args.length > 4 ? Integer.valueOf(args[4]) : 3;

        for (int i=0; i<loop_len; i++){
            JSONObject json = new JSONObject();

            json.put("ap",appkey);
            json.put("cm",generateComFields(mid,uid));
            JSONArray eventsArray = new JSONArray();

            int flag = rand.nextInt(2);
            System.out.println(flag);
            switch (flag){
                case 0:
                    //应用启动
                    if (rand.nextBoolean()) {
                        eventsArray.add(generateStart());
                    }
                    break;
                case 1:
                    //事件日志
                    //商品点击，展示
                    if (rand.nextBoolean()){
                        eventsArray.add(generateDisplay(newsid_length));
                    }
                    //商品详情页
                    if (rand.nextBoolean()) {
                        eventsArray.add(generateNewsDetail(newsid_length));
                    }
                    // 商品列表页
                    if (rand.nextBoolean()) {
                        eventsArray.add(generateNewList());
                    }
                    // 广告
                    if (rand.nextBoolean()) {
                        eventsArray.add(generateAd());
                    }
                    //消息通知
                    if (rand.nextBoolean()) {
                        eventsArray.add(generateNotification());
                    }
                    //用户前台活跃
                    if (rand.nextBoolean()) {
                        eventsArray.add(generatbeforeground());
                    }
                    //用户后台活跃
                    if (rand.nextBoolean()) {
                        eventsArray.add(generateBackground());
                    }
                    //故障日志
                    if (rand.nextBoolean()) {
                        eventsArray.add(generateError());
                    }
                    //用户评论
                    if (rand.nextBoolean()) {
                        eventsArray.add(generateComment());
                    }
                    //用户收藏
                    if (rand.nextBoolean()) {
                        eventsArray.add(generateFavorites());
                    }
                    //用户点赞
                    if (rand.nextBoolean()) {
                        eventsArray.add(generatePraise());
                    }
                    break;
            }
            json.put("et",eventsArray);

            //时间
            long millis = System.currentTimeMillis();
            //控制台打印
            logger.info(millis+"|"+json.toJSONString());
        }
    }

    /**
     *点赞
     * @return
     */
    private static JSONObject generatePraise() {
        AppPraise appPraise = new AppPraise();

        appPraise.setAdd_time((System.currentTimeMillis() - rand.nextInt(99999999))+"" );
        appPraise.setId(rand.nextInt(10));
        appPraise.setTarget_id(rand.nextInt(10));
        appPraise.setType(rand.nextInt(4)+1);
        appPraise.setUesrid(rand.nextInt(10));

        JSONObject jsonObject  = (JSONObject) JSON.toJSON(appPraise);
        return packEventJson("praise",jsonObject);
    }

    /**
     *收藏
     * @return
     */
    private static JSONObject generateFavorites() {
        AppFavorites appFavorites = new AppFavorites();

        appFavorites.setAdd_time((System.currentTimeMillis() - rand.nextInt(99999999))+"");
        appFavorites.setCourse_id(rand.nextInt(10));
        appFavorites.setId(Integer.valueOf(getRandomDigits(6)));
        appFavorites.setUser_id(rand.nextInt(10));

        JSONObject jsonObject = (JSONObject) JSON.toJSON(appFavorites);
        return packEventJson("favorites",jsonObject);
    }

    /**
     *评论
     * @return
     */
    private static JSONObject generateComment() {
        AppComment appComment = new AppComment();

        appComment.setComment_id(rand.nextInt(10));
        appComment.setUserid(rand.nextInt(10));
        appComment.setP_comment_id(rand.nextInt(5));
        appComment.setContent(getContent());

        appComment.setAddtime((System.currentTimeMillis() - rand.nextInt(99999999))+"");
        appComment.setOther_id(rand.nextInt(10));
        appComment.setPraise_count(rand.nextInt(1000));
        appComment.setReply_count(rand.nextInt(200));

        JSONObject jsonObject = (JSONObject) JSON.toJSON(appComment);

        return packEventJson("comment",jsonObject);
    }

    /**
     * 拼接成多个汉字
     * @return
     */
    private static String getContent() {
        String str = "";

        for (int i = 0; i < rand.nextInt(100); i++) {
            str += getRandomChar();
        }
        return str;
    }

    /**
     * 生成单个汉字
     * @return
     */
    private static char getRandomChar() {
        String str = "";
        int hightPos;
        int lowPos;

        Random random = new Random();
        hightPos = (176+Math.abs(rand.nextInt(39)));
        lowPos = (161 + Math.abs(random.nextInt(93)));

        byte[] b = new byte[2];
        b[0] = Integer.valueOf(hightPos).byteValue();
        b[1] = Integer.valueOf(lowPos).byteValue();
        try {
            str = new String(b,"GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("error");
        }

        return str.charAt(0);
    }


    /**
     *错误日志数据
     * @return
     */
    private static JSONObject generateError() {
        AppErrorLog appErrorLog = new AppErrorLog();

        //错误摘要
        String[] errorBriefs= {"at cn.lift.dfdf.web.AbstractBaseController.validInbound(AbstractBaseController.java:72)", "at cn.lift.appIn.control.CommandUtil.getInfo(CommandUtil.java:67)"};
        //错误详情
        String[] errorDetails = {"java.lang.NullPointerException\\n" + "at cn.lift.appIn.web.AbstractBaseController.validInbound(AbstractBaseController.java:72)\\n" + "at cn.lift.dfdf.web.AbstractBaseController.validInbound", "at cn.lift.dfdfdf.control.CommandUtil.getInfo(CommandUtil.java:67)\\n " + "at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\\n" + " at java.lang.reflect.Method.invoke(Method.java:606)\\n"};
        //错误摘要
        appErrorLog.setErrorBrief(errorBriefs[rand.nextInt(errorBriefs.length)]);
        //错误详情
        appErrorLog.setErrorDetail(errorDetails[rand.nextInt(errorDetails.length)]);

        JSONObject jsonObject = (JSONObject) JSON.toJSON(appErrorLog);
        return packEventJson("error",jsonObject);
    }

    /**
     *后台活跃
     * @return
     */
    private static JSONObject generateBackground() {
        AppActive_background activeBackground = new AppActive_background();

        //启动源
        activeBackground.setActive_source(rand.nextInt(3)+1+"");

        JSONObject jsonObject = (JSONObject) JSON.toJSON(activeBackground);
        return packEventJson("active_background",jsonObject);
    }

    /**
     *前台活跃
     * @return
     */
    private static JSONObject generatbeforeground() {
        AppActive_foreground activeForeground = new AppActive_foreground();

        //1.push 2.icon 3.其他
        activeForeground.setAccess(rand.nextInt(3)+1+"");

        //推送消息的id
        int flag = rand.nextInt(2);
        if (flag == 1) {
            activeForeground.setPush_id(getRandomDigits(6) + "");
        }else {
            activeForeground.setPush_id("");
        }

        JSONObject jsonObject = (JSONObject) JSON.toJSON(activeForeground);

        return packEventJson("active_foreground",jsonObject);
    }

    /**
     *消息通知
     * @return
     */
    private static JSONObject generateNotification() {
        AppNotification appNotification = new AppNotification();

        //动作
        appNotification.setAction(rand.nextInt(4)+1+"");
        //通知id
        appNotification.setType(rand.nextInt(4)+1+"");
        //消息通知时间
        appNotification.setAp_time((System.currentTimeMillis() - rand.nextInt(99999999)) + "");
        //备用字段

        appNotification.setContent("");

        JSONObject jsonObject = (JSONObject) JSON.toJSON(appNotification);

        return packEventJson("notification",jsonObject);
    }

    /**
     * 广告相关字段
     * @return
     */
    private static JSONObject generateAd() {
        AppAD appAD = new AppAD();

        //入口
        appAD.setEntry(rand.nextInt(3)+1+"");
        //动作
        appAD.setAction(rand.nextInt(5)+1+"");
        //状态
        int flag = rand.nextInt(100) > 50 ? 2 : 1;
        appAD.setContent(flag+"");
        //失败码
        flag = rand.nextInt(10);
        switch (flag) {
            case 1:appAD.setDetail("102");break;
            case 2:appAD.setDetail("201");break;
            case 3:appAD.setDetail("325");break;
            case 4:appAD.setDetail("433");break;
            case 5:appAD.setDetail("542");break;
            case 6:appAD.setDetail("200");break;
            case 7:appAD.setDetail("404");break;
            default:appAD.setDetail("");break;
        }
        //广告来源
        appAD.setSource(rand.nextInt(4)+1+"");
        //用户行为
        appAD.setBehavior(rand.nextInt(2)+1+"");
        //商品类型
        appAD.setNewstype(rand.nextInt(10)+"");
        //展示样品
        appAD.setShow_style(rand.nextInt(7)+"");

        JSONObject jsonObject = (JSONObject) JSON.toJSON(appAD);
        return packEventJson("ad",jsonObject);
    }

    /**
     *商品列表
     * @return
     */
    private static JSONObject generateNewList() {
        AppLoading appLoading = new AppLoading();

        //动作
        appLoading.setAction(rand.nextInt(3)+1+"");
        //加载时长
        appLoading.setLoading_time(rand.nextInt(10) * rand.nextInt(7)+"");
        //失败码
        int flag = rand.nextInt(10);
        switch (flag) {
            case 1:appLoading.setType1("102");break;
            case 2:appLoading.setType1("201");break;
            case 3:appLoading.setType1("325");break;
            case 4:appLoading.setType1("433");break;
            case 5:appLoading.setType1("542");break;
            case 6:appLoading.setType1("200");break;
            case 7:appLoading.setType1("404");break;
            default:appLoading.setType1("");break;
        }
        //加载类型
        appLoading.setLoading_way(rand.nextInt(2)+1+"");
        //扩展字段
        appLoading.setExtent1("");
        appLoading.setExtent2("");
        //用户加载类型
        appLoading.setType(rand.nextInt(3)+1+"");
        JSONObject jsonObject = (JSONObject) JSON.toJSON(appLoading);

        return packEventJson("loading",jsonObject);

    }

    /**
     * 商品详情
     * @param newsid_length
     * @return
     */
    private static JSONObject generateNewsDetail(int newsid_length) {
        AppNewsDetail newsDetail = new AppNewsDetail();

        //页面入口来源
        int flag = 1 + rand.nextInt(3);
        newsDetail.setEntry(""+ flag);

        //动作
        newsDetail.setAction(rand.nextInt(4)+1+"");

        //商品id
        newsDetail.setNewsid('n'+getRandomDigits(newsid_length));
        //商品样式
        newsDetail.setShowtype(rand.nextInt(6)+"");
        //页面停留时长
        newsDetail.setNews_staytime(rand.nextInt(10)*rand.nextInt(7)+"");
        //加载时长
        newsDetail.setLoading_time(rand.nextInt(10)*rand.nextInt(7)+"");

        //加载失败吗
        flag = rand.nextInt(10);
        switch (flag){
            case 1:newsDetail.setType1("102");break;
            case 2:newsDetail.setType1("201");break;
            case 3:newsDetail.setType1("325");break;
            case 4:newsDetail.setType1("433");break;
            case 5:newsDetail.setType1("542");break;
            case 6:newsDetail.setType1("200");break;
            case 7:newsDetail.setType1("404");break;
            default:newsDetail.setType1("");break;
        }

        //分类
        newsDetail.setCategory(1+rand.nextInt(100)+"");

        JSONObject eventJson = (JSONObject) JSON.toJSON(newsDetail);
        return packEventJson("newsdetail",eventJson);
    }

    /**
     * 商品展示事件
     * @param newsid_length
     * @return
     */
    private static JSONObject generateDisplay(int newsid_length) {
        AppDisplay appDisplay = new AppDisplay();

        boolean boolFlag = rand.nextInt(10) < 7 ? true : false;

        //动作：曝光商品=1，点击商品=2
        if (boolFlag) {
            appDisplay.setAction("1");
        }else {
            appDisplay.setAction("2");
        }

        //商品id
        String newsId = 'n' + getRandomDigits(newsid_length);
        appDisplay.setNewsid(newsId);

        //顺序 6
        int flag = rand.nextInt(6);
        appDisplay.setPlace(""+flag);

        //曝光类型
        flag = 1 + rand.nextInt(2);
        appDisplay.setExtend1(flag+"");

        //分类
        flag = 1 + rand.nextInt(100);
        appDisplay.setCategory(""+flag);

        JSONObject jsonObject = (JSONObject) JSON.toJSON(appDisplay);

        return packEventJson("display",jsonObject);
    }

    /**
     * 为各个事件类型的公共字段（时间、事件类型、Json数据）拼接
     * @param eventName
     * @param jsonObject
     * @return
     */
    private static JSONObject packEventJson(String eventName, JSONObject jsonObject) {
        JSONObject eventJson = new JSONObject();

        eventJson.put("ett",(System.currentTimeMillis() - rand.nextInt(99999999)) + "");
        eventJson.put("en",eventName);
        eventJson.put("kv",jsonObject);

        return eventJson;
    }

    private static String getRandomDigits(int length) {
        String result = "";

        for (int i = 0; i < length; i++) {
            result += rand.nextInt(10);
        }
        return result;
    }

    static JSONObject generateStart() {
        AppStart appStart = new AppStart();

//      入口
        int flag = rand.nextInt(5) + 1;
        appStart.setEntry(flag + "");

//      开屏广告类型
        flag = rand.nextInt(2) + 1;
        appStart.setOpen_ad_type(flag + "");

//      状态
        flag = rand.nextInt(10) > 8 ? 2 : 1;
        appStart.setAction(flag + "");

//      加载时长
        appStart.setLoading_time(rand.nextInt(20) + "");

        flag = rand.nextInt(10);
        switch (flag) {
            case 1:appStart.setDetail("102");break;
            case 2:appStart.setDetail("201");break;
            case 3:appStart.setDetail("325");break;
            case 4:appStart.setDetail("433");break;
            case 5:appStart.setDetail("542");break;
            case 6:appStart.setDetail("200");break;
            case 7:appStart.setDetail("404");break;
            default:appStart.setDetail("");break;
        }

        JSONObject jsonObject = (JSONObject) JSON.toJSON(appStart);

        return packEventJson("start", jsonObject);

    }

    /**
     * 获取随机字母组合
     *
     * @param length getRandomChar

     * @return
     */
    public static String getRandomChar(Integer length) {

        String str = "";
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            // 字符串
            str += (char) (65 + random.nextInt(26));// 取得大写字母
        }

        return str;
    }


    /**
     * 公共字段设置
     * @param mid
     * @param uid
     * @return
     */
    private static JSONObject generateComFields(int mid, int uid) {
        AppBase appBase = new AppBase();

        //设备id
        appBase.setMid('m'+getRandomDigits(mid));
        //用户id
        appBase.setUid('u'+getRandomDigits(uid));
        //程序版本号
        appBase.setVc(""+rand.nextInt(20));
        //程序版本名
        appBase.setVn("1."+rand.nextInt(4)+"."+rand.nextInt(10));
        //安卓系统版本
        appBase.setOs("8."+rand.nextInt(3)+"."+rand.nextInt(10));
        //语言，es，en，pt
        int flag = rand.nextInt(3);

        switch (flag){
            case (0):appBase.setLanguage("es");break;
            case (1):appBase.setLanguage("en");break;
            case (2):appBase.setLanguage("pt");break;
        }
        //      渠道号   从哪个渠道来的
        appBase.setSr(getRandomChar(1));

        //区域
        flag = rand.nextInt(2);
        switch (flag){
            case 0:appBase.setAr("BR");break;
            case 1:appBase.setAr("MX");break;
        }
        //手机品牌，手机型号md，取两位数字
        flag = rand.nextInt(3);
        switch (flag){
            case 0:
                appBase.setBa("sumsung");
                appBase.setMd("sumsung-"+rand.nextInt(20));
                break;
            case 1:
                appBase.setBa("huawei");
                appBase.setMd("huawei-"+rand.nextInt(20));
                break;
            case 2:
                appBase.setBa("HTC");
                appBase.setMd("HTC-"+rand.nextInt(20));
                break;
        }
        //sdk version
        appBase.setSv("v2."+rand.nextInt(10)+"."+rand.nextInt(10));
        //gmail
        appBase.setGmail(getRandomCharAndNumr(8)+"@gmail.com");
        //屏幕宽高
        flag = rand.nextInt(4);
        switch (flag){
            case 0:appBase.setHw("640*960");break;
            case 1:appBase.setHw("640*1136");break;
            case 2:appBase.setHw("750*1134");break;
            case 3:appBase.setHw("1080*1920");break;
        }
        //客户端产生日志时间
        long timeMillis = System.currentTimeMillis();
        appBase.setTime(""+(timeMillis-rand.nextInt(99999999)));

        //手机网络模式，3G,4G,WIFI
        flag = rand.nextInt(3);
        switch (flag){
            case 0:appBase.setNw("3G");break;
            case 1:appBase.setNw("4G");break;
            case 2:appBase.setNw("WIFI");break;
        }

        //      拉丁美洲 西经34°46′至西经117°09；北纬32°42′至南纬53°54′ 经度
        appBase.setLn((-34 - rand.nextInt(83) - rand.nextInt(60) / 10.0) + "");
        //维度
        appBase.setLa((32 - rand.nextInt(85) - rand.nextInt(60) / 10.0) + "");
        JSONObject common = (JSONObject) JSON.toJSON(appBase);
        return common;
    }

    /**
     * 获取随机字母数字组合
     * @param length
     * @return
     */
    private static String getRandomCharAndNumr(int length) {
        String str = "";
        Random random = new Random();

        for (int i = 0; i < length; i++) {

            boolean b = random.nextBoolean();

            if (b) { // 字符串
                // int choice = random.nextBoolean() ? 65 : 97; 取得65大写字母还是97小写字母
                str += (char) (65 + random.nextInt(26));// 取得大写字母
            } else { // 数字
                str += String.valueOf(random.nextInt(10));
            }
        }

        return str;
    }
}
