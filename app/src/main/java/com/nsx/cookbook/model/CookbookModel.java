package com.nsx.cookbook.model;

import com.google.gson.Gson;
import com.nsx.cookbook.app.Config;
import com.nsx.cookbook.base.ResponseCallBack;
import com.nsx.cookbook.bean.FoodDetailBean;
import com.nsx.cookbook.interfaces.BeanCallBack;
import com.nsx.cookbook.interfaces.ICookbookModel;
import com.nsx.cookbook.service.ICookbookService;
import com.nsx.cookbook.utils.RetrofitUtils;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/4/20.
 * 食谱网络请求
 */

public class CookbookModel implements ICookbookModel {

    private ICookbookService cookbookService;

    private static CookbookModel instance = new CookbookModel();

    public static CookbookModel getInstance() {
        return instance;
    }

    private CookbookModel() {
    }

    @Override
    public void cookBookById(int classid, int start, int num, final BeanCallBack<FoodDetailBean> callback) {
        //得到Retrofit对象
        Retrofit retrofit = RetrofitUtils.getInstance().getGsonRetrofit();
        //创建请求接口
        cookbookService = retrofit.create(ICookbookService.class);

        Call<FoodDetailBean> call = cookbookService.cookBookById(classid, start, num, Config.COOK_APP_KEY);
        call.enqueue(new ResponseCallBack<FoodDetailBean>() {
            @Override
            public void onResponse(Call<FoodDetailBean> call, Response<FoodDetailBean> response) {
                if (response.body().getMsg().equals("ok")) {
                    callback.onSucceed(response.body());
                } else {
                    callback.onError("数据请求成功，但数据错误");
                }
            }
        });
    }

    @Override
    public void cookBookSearch(String keyword, int num, final BeanCallBack<FoodDetailBean> callback) {
        //得到Retrofit对象
        Retrofit retrofit = RetrofitUtils.getInstance().getGsonRetrofit();
        //创建请求接口
        cookbookService = retrofit.create(ICookbookService.class);
        Call<FoodDetailBean> call = cookbookService.cookBookSearch(keyword, num, Config.COOK_APP_KEY);
        call.enqueue(new ResponseCallBack<FoodDetailBean>() {
            @Override
            public void onResponse(Call<FoodDetailBean> call, Response<FoodDetailBean> response) {
                if (response.body().getMsg().equals("ok")) {
                    callback.onSucceed(response.body());
                } else {
                    callback.onError("数据请求成功，但数据错误");
                }
            }
        });
    }

    @Override
    public void cookBookDetail(int id, final BeanCallBack<FoodDetailBean.ResultBean.ListBean> callback) {
        //解析Json字符串 将 返回的Json数据中的 "result"字段转换为 ListBean对象
        //得到Retrofit 的字符串处理对象
        Retrofit retrofit = RetrofitUtils.getInstance().getStringRetrofit();
        //创建请求接口
        cookbookService = retrofit.create(ICookbookService.class);
        Call<String> call = cookbookService.cookBookDetail(id, Config.COOK_APP_KEY);
        call.enqueue(new ResponseCallBack<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //将请求的数据解析为ListBean对象 因为请求数据并非对应 FoodDetailBean 无法直接转换
                parserUserBean(response.body(), callback);
            }
        });
    }

    private void parserUserBean(String json, BeanCallBack<FoodDetailBean.ResultBean.ListBean> callback) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            String msg = jsonObject.optString("msg");
            if (msg.equals("ok")) {
                //将"result"字段转换为 ListBean 对象
                Gson gson = new Gson();
                String result = jsonObject.optString("result");
                FoodDetailBean.ResultBean.ListBean listbean = gson.fromJson(result,
                        FoodDetailBean.ResultBean.ListBean.class);
                callback.onSucceed(listbean);
            } else {
                callback.onError(msg);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            callback.onError("Json数据格式错误");
        }
    }
}
