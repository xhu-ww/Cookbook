package com.nsx.cookbook.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/6.
 */

public class LaunarCalendar {

    /**
     * status : 0
     * msg : ok
     * result : {"year":"2017","month":"5","day":"6","yangli":"公元2017年05月06日","nongli":"农历二〇一七年四月十一","star":"金牛座","taishen":"占房床房内北","wuxing":"长流水","chong":"冲（丁亥）猪","sha":"煞东","shengxiao":"鸡","jiri":"勾陈（黑道）建日","zhiri":"勾陈（黑道凶日）","xiongshen":"月建 小时 土府 重日 勾陈","jishenyiqu":"王日","caishen":"正南","xishen":"东南","fushen":"正西","suici":["丁酉年","乙巳月","癸巳日"],"yi":["塞穴","断蚁","结网","畋猎","余事勿取"],"ji":["嫁娶","安葬","入宅","出行","动土","词讼"],"eweek":"SATURDAY","emonth":"May","week":"六"}
     */

    private String status;
    private String msg;
    private ResultBean result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * year : 2017
         * month : 5
         * day : 6
         * yangli : 公元2017年05月06日
         * nongli : 农历二〇一七年四月十一
         * star : 金牛座
         * taishen : 占房床房内北
         * wuxing : 长流水
         * chong : 冲（丁亥）猪
         * sha : 煞东
         * shengxiao : 鸡
         * jiri : 勾陈（黑道）建日
         * zhiri : 勾陈（黑道凶日）
         * xiongshen : 月建 小时 土府 重日 勾陈
         * jishenyiqu : 王日
         * caishen : 正南
         * xishen : 东南
         * fushen : 正西
         * suici : ["丁酉年","乙巳月","癸巳日"]
         * yi : ["塞穴","断蚁","结网","畋猎","余事勿取"]
         * ji : ["嫁娶","安葬","入宅","出行","动土","词讼"]
         * eweek : SATURDAY
         * emonth : May
         * week : 六
         */

        private String year;
        private String month;
        private String day;
        private String yangli;
        private String nongli;
        private String star;
        private String taishen;
        private String wuxing;
        private String chong;
        private String sha;
        private String shengxiao;
        private String jiri;
        private String zhiri;
        private String xiongshen;
        private String jishenyiqu;
        private String caishen;
        private String xishen;
        private String fushen;
        private String eweek;
        private String emonth;
        private String week;
        private List<String> suici;
        private List<String> yi;
        private List<String> ji;

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getYangli() {
            return yangli;
        }

        public void setYangli(String yangli) {
            this.yangli = yangli;
        }

        public String getNongli() {
            return nongli;
        }

        public void setNongli(String nongli) {
            this.nongli = nongli;
        }

        public String getStar() {
            return star;
        }

        public void setStar(String star) {
            this.star = star;
        }

        public String getTaishen() {
            return taishen;
        }

        public void setTaishen(String taishen) {
            this.taishen = taishen;
        }

        public String getWuxing() {
            return wuxing;
        }

        public void setWuxing(String wuxing) {
            this.wuxing = wuxing;
        }

        public String getChong() {
            return chong;
        }

        public void setChong(String chong) {
            this.chong = chong;
        }

        public String getSha() {
            return sha;
        }

        public void setSha(String sha) {
            this.sha = sha;
        }

        public String getShengxiao() {
            return shengxiao;
        }

        public void setShengxiao(String shengxiao) {
            this.shengxiao = shengxiao;
        }

        public String getJiri() {
            return jiri;
        }

        public void setJiri(String jiri) {
            this.jiri = jiri;
        }

        public String getZhiri() {
            return zhiri;
        }

        public void setZhiri(String zhiri) {
            this.zhiri = zhiri;
        }

        public String getXiongshen() {
            return xiongshen;
        }

        public void setXiongshen(String xiongshen) {
            this.xiongshen = xiongshen;
        }

        public String getJishenyiqu() {
            return jishenyiqu;
        }

        public void setJishenyiqu(String jishenyiqu) {
            this.jishenyiqu = jishenyiqu;
        }

        public String getCaishen() {
            return caishen;
        }

        public void setCaishen(String caishen) {
            this.caishen = caishen;
        }

        public String getXishen() {
            return xishen;
        }

        public void setXishen(String xishen) {
            this.xishen = xishen;
        }

        public String getFushen() {
            return fushen;
        }

        public void setFushen(String fushen) {
            this.fushen = fushen;
        }

        public String getEweek() {
            return eweek;
        }

        public void setEweek(String eweek) {
            this.eweek = eweek;
        }

        public String getEmonth() {
            return emonth;
        }

        public void setEmonth(String emonth) {
            this.emonth = emonth;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public List<String> getSuici() {
            return suici;
        }

        public void setSuici(List<String> suici) {
            this.suici = suici;
        }

        public List<String> getYi() {
            return yi;
        }

        public void setYi(List<String> yi) {
            this.yi = yi;
        }

        public List<String> getJi() {
            return ji;
        }

        public void setJi(List<String> ji) {
            this.ji = ji;
        }
    }
}
