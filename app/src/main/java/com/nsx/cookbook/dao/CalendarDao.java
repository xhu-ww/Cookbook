package com.nsx.cookbook.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nsx.cookbook.bean.custom.LocalCalendar;

/**
 * Created by loptop on 2017/5/1.
 */

public class CalendarDao {
    MySqliteOpenHelper mHelper;

    public CalendarDao(Context context) {
        mHelper = new MySqliteOpenHelper(context);
    }

    /**
     * 向数据库中添加数据
     *
     * @param calendar
     */
    public void addCalendar(LocalCalendar calendar) {
        SQLiteDatabase database = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //存入具体数据
        values.put("date", calendar.getDate());
        values.put("gregorian_calendar", calendar.getGregorianCalendar());
        values.put("chinese_calendar", calendar.getChineseCalendar());
        values.put("solar_terms", calendar.getSolarTerms());
        values.put("luck", calendar.getLuck());
        values.put("unlucky", calendar.getUnlucky());
        //向表中插入数据
        database.insert(MySqliteOpenHelper.CALENDAR, null, values);
        database.close();
    }

    /**
     * 更新表中的数据
     *
     * @param calendar
     */
    public void updateCalendar(LocalCalendar calendar) {
        SQLiteDatabase database = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("date", calendar.getDate());
        values.put("gregorian_calendar", calendar.getGregorianCalendar());
        values.put("chinese_calendar", calendar.getChineseCalendar());
        values.put("solar_terms", calendar.getSolarTerms());
        values.put("luck", calendar.getLuck());
        values.put("unlucky", calendar.getUnlucky());
        database.update(MySqliteOpenHelper.CALENDAR, values, "id=?", new String[]{"1"});
        database.close();
    }

    /**
     * 更新表中的数据
     *
     * @return
     */
    public LocalCalendar getLocalCalendar() {
        LocalCalendar calendar = new LocalCalendar();
        SQLiteDatabase database = mHelper.getReadableDatabase();
        //游标
        Cursor cursor = database.query(MySqliteOpenHelper.CALENDAR, null, null, null, null, null, null);
        //如果还有下一个
        while (cursor.moveToNext()) {
            calendar.setDate(cursor.getString(cursor.getColumnIndex("date")));
            calendar.setGregorianCalendar(cursor.getString(cursor.getColumnIndex("gregorian_calendar")));
            calendar.setChineseCalendar(cursor.getString(cursor.getColumnIndex("chinese_calendar")));
            calendar.setSolarTerms(cursor.getString(cursor.getColumnIndex("solar_terms")));
            calendar.setLuck(cursor.getString(cursor.getColumnIndex("luck")));
            calendar.setUnlucky(cursor.getString(cursor.getColumnIndex("unlucky")));
        }
        return calendar;
    }
}

