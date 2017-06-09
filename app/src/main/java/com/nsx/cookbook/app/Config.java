package com.nsx.cookbook.app;

/**
 * Created by Administrator on 2017/4/19.
 */

public class Config {

    /****************************************食谱***************************************************/

    //服务器基地址
    public static final String COOK_BASE_URL = "http://api.jisuapi.com/recipe/";
    //APP KEY
    public static final String COOK_APP_KEY = "b1156a2e4c184171";
    //分类标签ID查询
    public static final String BYCLASSID = "byclass";
    //菜谱搜索
    public static final String SEARCH = "search";
    //通过事物ID查询细节
    public static final String DETAIL = "detail";
    /****************************************图灵机器人*************************************************/
    //APP_KEY 和食谱的基地址一样
    //图灵机器人请求服务器基地址
    public static final String ROBOT_BASE_URL = "http://api.jisuapi.com/iqa/";
    public static final String ROBOT_QUERY = "query";
    /****************************************历史上的今天*************************************************/
    //APP_KEY 和食谱的基地址一样
    //历史上的今天请求服务器基地址
    public static final String HISTORY_BASE_URL = "http://api.jisuapi.com/todayhistory/";
    public static final String HISTORY_QUERY = "query";

    /****************************************日历***************************************************/

    //服务器基地址 聚源数据的 主要是为了得到24节气
    public static final String CALENDAR_BASE_URL = "http://route.showapi.com/";
    public static final String CALENDAR = "856-1";
    public static final String SHOWAPI_APPID = "36980";
    public static final String SHOWAPI_SIGN = "dc0dab968257484c86f481b176d49106";
    //服务器基地址 极速数据的 主要是为了得到 宜做之事和不宜做的事
    public static final String LAUNAR_BASE_URL = "http://api.jisuapi.com/huangli/";
    public static final String LAUNAR_DATE = "date";

    /****************************************广播***************************************************/
    //事物收藏数据改变的 广播动作名
    public static String BroadcastAction = "food_collection_data_change";
    /****************************************搜索记录的文件名****************************************/
    public static String SEARCHFILENAME = "search_record.txt";
}
