package com.nsx.cookbook.interfaces;


import com.nsx.cookbook.bean.FoodDetailBean;

/**
 * Created by Administrator on 2017/4/21.
 */

public interface ICookbookModel {

    /**
     * 按分类标签ID查询菜谱
     *
     */
    public void cookBookById(int classid, int start, int num,BeanCallBack<FoodDetailBean> callback);
    /**
     * 按照搜索文本查询菜谱
     *
     */
    public void cookBookSearch(String keyword,  int num, BeanCallBack<FoodDetailBean> callback);
    /**
     * 按照食物ID查询菜谱
     *
     */
    public void cookBookDetail(int id, BeanCallBack<FoodDetailBean.ResultBean.ListBean> callback);

}
