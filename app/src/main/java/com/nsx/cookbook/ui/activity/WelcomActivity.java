package com.nsx.cookbook.ui.activity;

import android.content.SharedPreferences;
import android.widget.ImageView;

import com.nsx.cookbook.R;
import com.nsx.cookbook.app.AppApplication;
import com.nsx.cookbook.base.BaseActivity;
import com.nsx.cookbook.bean.CalendarBean;
import com.nsx.cookbook.bean.LaunarCalendar;
import com.nsx.cookbook.bean.custom.LocalCalendar;
import com.nsx.cookbook.dao.CalendarDao;
import com.nsx.cookbook.dao.FoodCollectDao;
import com.nsx.cookbook.interfaces.BeanCallBack;
import com.nsx.cookbook.model.CalendarModel;
import com.nsx.cookbook.utils.L;
import com.nsx.cookbook.utils.NetUtils;
import com.nsx.cookbook.utils.StringUtils;

import java.util.Date;

import butterknife.BindView;

/**
 * Created by loptop on 2017/4/27.
 */

public class WelcomActivity extends BaseActivity implements Runnable {
    @BindView(R.id.iv_welcome_bg)
    ImageView mIvWelcomeBg;
    //数据库操作
    CalendarDao calendarDao;
    FoodCollectDao foodCollectDao;
    //判断是否为第一次进入App
    private boolean firstInstall;
    //判断是否同一天
    private boolean isToday;

    @Override
    protected void initView() {
        calendarDao = new CalendarDao(this);
        foodCollectDao = new FoodCollectDao(this);
        //7张图片随机一张作为启动页
        int[] images = {R.mipmap.day1, R.mipmap.day2, R.mipmap.day3, R.mipmap.day4,
                R.mipmap.day5, R.mipmap.day6, R.mipmap.day7};
        int number = StringUtils.getRandomNumber(0, images.length - 1);
        mIvWelcomeBg.setImageResource(images[number]);
        //设置启动动画
        mIvWelcomeBg.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();

        new Thread(this).start();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    /**
     * 是否为第一次安装
     */
    private void isFirstInstall() {
        SharedPreferences sharedPreferences = getSharedPreferences("flag", MODE_PRIVATE);
        firstInstall = sharedPreferences.getBoolean("first", true);
        // 判断是否首次安装
        if (firstInstall) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean("first", false);
            edit.apply();
        }
    }

    /**
     * 判断当前数据库内的日期时间 是否与当前时间匹配
     */
    private void judgeIsToday() {
        //得到 今天的日期 2017-05-01
        String date = StringUtils.getYTDDateString(new Date());
        // 去掉 "-"
        date = StringUtils.removeOtherCode(date, "-");
        L.e("-------------天数---" + date);
        //从数据库中取出数据
        LocalCalendar calendar = calendarDao.getLocalCalendar();
        L.e("-------------天数---" + calendarDao.getLocalCalendar().getDate());
        isToday = date.equals(calendar.getDate());
        if (calendar != null) {
            //如果是同一天就取本地数据
            if (isToday) {
                AppApplication.getInstance().setLocalDate(calendarDao.getLocalCalendar());
                L.e("----------------得到本地数据" + calendarDao.getLocalCalendar());
            } else {
                getCalendarData(date);
                L.e("-------------天数不同---");
            }
        } else {
            //如果数据库为空，也请求数据
            getCalendarData(date);
            L.e("-------------没得到本地数据---");
        }
    }

    /**
     * 网络请求日期数据
     */
    public void getCalendarData(final String date) {
        //菜谱相关网络请求
        CalendarModel calendarModel = CalendarModel.getInstance();
        //如果没有网,全设为空
        if (!NetUtils.isConnected(this)) {
            LocalCalendar calendar; calendar = new LocalCalendar();
            calendar.setDate(" ");
            String today = StringUtils.getDateString(new Date());
            calendar.setGregorianCalendar(today);
            calendar.setChineseCalendar("未连接网络");
            calendar.setSolarTerms("未连接网络");
            calendar.setLuck("未连接网络");
            calendar.setUnlucky("未连接网络");
            //存储日期数据
            AppApplication.getInstance().setLocalDate(calendar);
        } else {
            final LocalCalendar calendar = new LocalCalendar();
            //请求日历
            calendarModel.getCalendar(date, new BeanCallBack<CalendarBean>() {
                @Override
                public void onSucceed(CalendarBean calendarBean) {
                    CalendarBean.ShowapiResBodyBean netDate = calendarBean.getShowapi_res_body();
                    //设置今天的时间
                    calendar.setDate(date);
                    calendar.setGregorianCalendar(netDate.getGongli());
                    calendar.setChineseCalendar(netDate.getNongli());
                    calendar.setSolarTerms(netDate.getJieqi24());
                }

                @Override
                public void onError(String msg) {

                }
            });
            //请求农历
            //得到 今天的日期 2017-05-01
            String today = StringUtils.getYTDDateString(new Date());
            //转换为 年月日
            String[] dates = today.split("-");
            calendarModel.getLaunarCalendar(dates[0], dates[1], dates[2], new BeanCallBack<LaunarCalendar>() {
                @Override
                public void onSucceed(LaunarCalendar launarCalendar) {
                    //宜 去掉首尾的"[ ]"
                    calendar.setLuck(StringUtils.removeOtherCode(launarCalendar.getResult().getYi().toString(), "\\[|\\]"));
                    //忌
                    calendar.setUnlucky(StringUtils.removeOtherCode(launarCalendar.getResult().getJi().toString(), "\\[|\\]"));
                    //最后保存数据
                    //存储日期数据
                    AppApplication.getInstance().setLocalDate(calendar);
//        如果首次安装 就存入数据库，如果不熟首次安装 就更新数据库
                    if (firstInstall) {
                        calendarDao.addCalendar(calendar);
                        L.e("----------------存储成功");
                    } else {
                        calendarDao.updateCalendar(calendar);
                        L.e("--------更新-" + calendar.toString());
                    }
                }

                @Override
                public void onError(String msg) {

                }
            });
        }
    }

    @Override
    public void run() {
        //判断app是否为第一次安装
        isFirstInstall();
        //判断是否数据库的日历数据与当前是否未同一天
        judgeIsToday();
        //得到本地收藏的菜谱数据集合
        AppApplication.getInstance().setFoodCollections(foodCollectDao.getFoodCollection());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //跳转界面
        if (firstInstall) {
            startActivity(GuideActivity.class);
        } else {
            startActivity(MainActivity.class);
        }
        finish();
    }
}