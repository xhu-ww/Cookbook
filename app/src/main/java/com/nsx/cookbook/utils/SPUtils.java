package com.nsx.cookbook.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences 存储数据 只能存储基本数据类型
 */

public class SPUtils {
    SharedPreferences mSharedPreferences;
    //name为存储的文件名
    public  SPUtils(Context context,String name){
        //MODE_PRIVATE 操作模式，表示只有当前应用程序可以进行读写，写入的新内容覆盖原有内容
        mSharedPreferences = context.getSharedPreferences(name,Context.MODE_PRIVATE);
    }

    /**
     * 保存数据
     * @param values
     */
    public void save(KeyValue... values) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        for (KeyValue keyValue : values) {
            if (keyValue.value instanceof Integer) {
                editor.putInt(keyValue.key,Integer.parseInt(keyValue.value.toString()));
            }
            if (keyValue.value instanceof Boolean) {
                editor.putBoolean(keyValue.key,Boolean.parseBoolean(keyValue.value.toString()));
            }
            if (keyValue.value instanceof String) {
                editor.putString(keyValue.key,keyValue.value.toString());
            }
        }
    }

    /**
     * 得到字符串类型数据
     * @param key
     * @return
     */
    public String getString(String key) {
        return mSharedPreferences.getString(key, null);
    }

    public boolean getBoolean(String key) {
        //找不到数据则返回 false
        return mSharedPreferences.getBoolean(key, false);
    }

    /**
     * 得到boolean类型数据
     * @param key
     * @param def 找不到数据时 返回的值
     * @return
     */
    public boolean getBoolean(String key, boolean def) {
        return mSharedPreferences.getBoolean(key, def);
    }

    public int getInt(String key) {
        return mSharedPreferences.getInt(key, 0);
    }

    /**
     * 清除数据
     */
    public void clear() {
        mSharedPreferences.edit().clear().commit();
    }

    public static class KeyValue{
        String key;
        Object value;

        public KeyValue(String key, Object value) {
            this.key = key;
            this.value = value;
        }
    }
}
