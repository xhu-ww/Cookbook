package com.nsx.cookbook.interfaces;

import com.nsx.cookbook.bean.RobotAnswer;


public interface TuringRobotModel {
    /**
     * 图灵机器人
     */
    public void getRobotAnswer(String question, BeanCallBack<RobotAnswer> callback);
}
