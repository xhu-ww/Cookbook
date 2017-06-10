package com.nsx.cookbook.model;

import com.nsx.cookbook.app.Config;
import com.nsx.cookbook.base.ResponseCallBack;
import com.nsx.cookbook.bean.RobotAnswer;
import com.nsx.cookbook.interfaces.BeanCallBack;
import com.nsx.cookbook.service.TuringRobotService;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TuringRobotModel implements com.nsx.cookbook.interfaces.TuringRobotModel {
    private TuringRobotService mIOtherNetService;

    private static TuringRobotModel instance = new TuringRobotModel();

    public static TuringRobotModel getInstance() {
        return instance;
    }

    private TuringRobotModel() {
        //得到Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.ROBOT_BASE_URL)
                //添加转换器，直接将内容转换成json 
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //创建请求接口
        mIOtherNetService = retrofit.create(TuringRobotService.class);
    }

    @Override
    public void getRobotAnswer(String question, final BeanCallBack<RobotAnswer> callback) {
        Call<RobotAnswer> call = mIOtherNetService.getRobotAnswer(Config.COOK_APP_KEY,question);
        call.enqueue(new ResponseCallBack<RobotAnswer>() {
            @Override
            public void onResponse(Call<RobotAnswer> call, Response<RobotAnswer> response) {

                if (response.body().getMsg().equals("ok")) {
                    callback.onSucceed(response.body());
                } else {
                    callback.onError("数据请求成功，但数据错误");
                }
            }
        });
    }
}
