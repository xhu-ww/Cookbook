package com.nsx.cookbook.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nsx.cookbook.bean.custom.FoodCollection;

import java.util.ArrayList;
import java.util.List;


public class FoodCollectDao {
    MySqliteOpenHelper mHelper;

    public FoodCollectDao(Context context) {
        mHelper = new MySqliteOpenHelper(context);
    }

    /**
     * 向数据库中添加数据
     *
     * @param food
     */
    public void addFoodCollection(FoodCollection food) {
        SQLiteDatabase database = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //存入具体数据
        values.put("food_id", food.getId());
        values.put("name", food.getName());
        values.put("image", food.getImage());
        //向表中插入数据
        database.insert(MySqliteOpenHelper.FOOD_COLLECTION, null, values);
        database.close();
    }

    /**
     * 删除表中的数据
     *
     * @param id 为食物id
     */
    public void deleteFodCollection(String id) {
        SQLiteDatabase database = mHelper.getWritableDatabase();
        database.delete(MySqliteOpenHelper.FOOD_COLLECTION, "food_id=?", new String[]{id});
        database.close();
    }

    /**
     * 将数据从表中取出
     */
    public List<FoodCollection> getFoodCollection() {
        List<FoodCollection> list = new ArrayList<>();
        SQLiteDatabase database = mHelper.getReadableDatabase();
        //游标
        Cursor cursor = database.query(MySqliteOpenHelper.FOOD_COLLECTION, null, null, null, null, null, null);
        //如果还有下一个
        while (cursor.moveToNext()) {
            FoodCollection food = new FoodCollection();
            food.setId(cursor.getString(cursor.getColumnIndex("food_id")));
            food.setName(cursor.getString(cursor.getColumnIndex("name")));
            food.setImage(cursor.getString(cursor.getColumnIndex("image")));
            list.add(food);
        }
        return list;
    }
}

