package com.nsx.cookbook.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/4/24.
 * <p>
 * <p>
 * <p>
 * id : 1001
 * title : 糖醋小排
 * tags : 浙菜;热菜;儿童;酸甜;快手菜
 * imtro : 糖醋小排，我估计爱吃的人太多了，要想太多的口味都会不同的，可以适当微调一下哈！
 * ingredients : 肋排,500g
 * burden : 葱,适量;白芝麻,适量;盐,3g;生粉,4;姜,3片;;生抽,15ml;生粉,7g;姜,适量
 * albums : ["http://juheimg.oss-cn-1/1001_253951.jpg"]
 * steps : [{"5.放入适量炸好的排骨，倒入调料汁，煮至汤汁浓稠时，关火，撒入葱花、白芝麻点缀即可"}]
 */

public class MySqliteOpenHelper extends SQLiteOpenHelper {

    //数据库名称  
    private static final String name = "cookbook.db";
    //数据库版本  
    private static final int version = 1;

    //日历信息表
    public static String CALENDAR = "calendar";
    //菜谱搜藏表
    public static String FOOD_COLLECTION = "collection";

    public MySqliteOpenHelper(Context context) {
        super(context, name, null, version);
    }

    //用于初次使用软件时生成数据库表  
    @Override
    public void onCreate(SQLiteDatabase db) {
        //执行SQL语句 创建表 
        //创建存储日历数据的表
        db.execSQL("create table " + CALENDAR + "(id integer primary key autoincrement" +
                ",date text" +
                ",gregorian_calendar text" +
                ",chinese_calendar text" +
                ",solar_terms text,luck text" +
                ",unlucky text)");
        //创建存储菜谱收藏数据的表
        db.execSQL("create table " + FOOD_COLLECTION + "(id integer primary key autoincrement" +
                ",food_id text" +
                ",name text" +
                ",image text)");
    }


    //用于升级软件时更新数据库表结构 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
