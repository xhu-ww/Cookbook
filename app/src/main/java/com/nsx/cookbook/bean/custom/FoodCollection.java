package com.nsx.cookbook.bean.custom;

/**
 * Created by Administrator on 2017/5/2.
 * 食谱收藏
 */

public class FoodCollection {
    //菜对应的ID
    private String id;
    //菜名
    private String name;
    //菜图片
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
