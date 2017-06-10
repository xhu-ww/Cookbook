package com.nsx.cookbook.bean.custom;


public class LocalCalendar {
    //记录今天是哪一天
    private String date;
    //公历
    private String gregorianCalendar;
    //农历
    private String chineseCalendar;
    //节气
    private String solarTerms;
    //宜
    private String luck;
    //忌
    private String unlucky;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGregorianCalendar() {
        return gregorianCalendar;
    }

    public void setGregorianCalendar(String gregorianCalendar) {
        this.gregorianCalendar = gregorianCalendar;
    }

    public String getChineseCalendar() {
        return chineseCalendar;
    }

    public void setChineseCalendar(String chineseCalendar) {
        this.chineseCalendar = chineseCalendar;
    }

    public String getSolarTerms() {
        return solarTerms;
    }

    public void setSolarTerms(String solarTerms) {
        this.solarTerms = solarTerms;
    }

    public String getLuck() {
        return luck;
    }

    public void setLuck(String luck) {
        this.luck = luck;
    }

    public String getUnlucky() {
        return unlucky;
    }

    public void setUnlucky(String unlucky) {
        this.unlucky = unlucky;
    }

    @Override
    public String toString() {
        return "LocalCalendar{" +
                "date='" + date + '\'' +
                ", gregorianCalendar='" + gregorianCalendar + '\'' +
                ", chineseCalendar='" + chineseCalendar + '\'' +
                ", solarTerms='" + solarTerms + '\'' +
                ", luck='" + luck + '\'' +
                ", unlucky='" + unlucky + '\'' +
                '}';
    }
}
