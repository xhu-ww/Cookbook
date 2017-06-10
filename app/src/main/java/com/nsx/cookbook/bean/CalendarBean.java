package com.nsx.cookbook.bean;


public class CalendarBean {


    private int showapi_res_code;
    private String showapi_res_error;
    private ShowapiResBodyBean showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {
        /**
         * t3 :
         * 吉凶：凶
         * 时柱：甲寅(大溪水)
         * 冲煞：冲猴煞北
         * 正冲：戊申(1968 2028)
         * 时宜：求嗣 嫁娶 移徙 入宅 开市 交易 修造 安葬 见贵 求财
         * 时忌：赴任 出行
         * 吉神：长生 驿马 左辅
         * 凶煞：不遇
         * 财喜：财神东南 喜神东北
         * <p>
         * t21 :
         * 吉凶：凶
         * 时柱：癸亥(大海水)
         * 冲煞：冲蛇煞西
         * 正冲：丁巳(1977 2037)
         * 时宜：赴任 见贵 出行 求财 嫁娶 进人口 移徙 安葬
         * 时忌：朱雀须用 凤凰符制 否则 诸事不宜 祭祀 祈福 斋醮 酬神
         * 吉神：左辅
         * 凶煞：朱雀 狗食 路空
         * 财喜：财神正南 喜神东南
         * <p>
         * t1 :
         * 吉凶：吉
         * 时柱：癸丑(桑柘木)
         * 冲煞：冲羊煞东
         * 正冲：丁未(1967 2027)
         * 时宜：合脊 嫁娶 修造 安葬
         * 时忌：祭祀 祈福 斋醮 开光 赴任 出行
         * 吉神：天德 功曹 宝光 贵人 天乙 五合 六合 合局
         * 凶煞：
         * 财喜：财神正南 喜神东南
         * <p>
         * t23 :
         * 吉凶：吉
         * 时柱：壬子(桑柘木)
         * 冲煞：冲马煞南
         * 正冲：丙午(1966 2026)
         * 时宜：订婚 嫁娶 开市 安葬 见贵 求财
         * 时忌：祭祀 祈福 斋醮 开光 赴任 出行
         * 吉神：金匮 大进 唐符
         * 凶煞：路空
         * 财喜：财神正南 喜神正南
         * <p>
         * nongli : 二零一七年 四月(大)初六
         * jieqi24 : 立夏：5月5日 小满：5月21日
         * nayin : [年]山下火 [月]覆灯火 [日]霹雳火
         * tszf : 房床碓外正北
         * shengxiao : 属鸡
         * xingzuo : 金牛座
         * ganzhi : 丁酉年 甲辰月 戊子日
         * t11 :
         * 吉凶：吉 旬空
         * 时柱：戊午(天上火)
         * 冲煞：冲鼠煞北
         * 正冲：壬子(1972 2032)
         * 时宜：
         * 时忌：日时相冲 诸事不宜
         * 吉神：司命 天厨 帝旺 比肩
         * 凶煞：日破 雷兵 六戊 旬空
         * 财喜：财神正北 喜神东南
         * <p>
         * t13 :
         * 吉凶：凶 旬空
         * 时柱：己未(天上火)
         * 冲煞：冲牛煞西
         * 正冲：癸丑(1973 2033)
         * 时宜：祈福 求嗣 求财 嫁娶 安葬
         * 时忌：赴任 出行 修造
         * 吉神：罗纹 交贵
         * 凶煞：勾陈 日害
         * 财喜：财神正北 喜神东北
         * <p>
         * t15 :
         * 吉凶：吉
         * 时柱：庚申(石榴木)
         * 冲煞：冲虎煞南
         * 正冲：甲寅(1974 2034)
         * 时宜：祈福 求嗣 订婚 嫁娶 出行 求财 开市 交易 安床 祭祀
         * 时忌：修造 动土
         * 吉神：三合 福星 青龙
         * 凶煞：地兵
         * 财喜：财神正东 喜神西北
         * <p>
         * sheng12 : 成执位
         * t17 :
         * 吉凶：吉
         * 时柱：辛酉(石榴木)
         * 冲煞：冲兔煞东
         * 正冲：乙卯(1975 2035)
         * 时宜：修造 盖屋 移徙 作灶 安床 入宅 开市 赴任 出行 求财 嫁娶
         * 时忌：祭祀 祈福 斋醮 酬神
         * 吉神：明堂 贪狼
         * 凶煞：天贼
         * 财喜：财神正南 喜神西南
         * <p>
         * pzbj : 戊不受田田主不祥 子不问卜自惹祸殃
         * t19 :
         * 吉凶：凶
         * 时柱：壬戌(大海水)
         * 冲煞：冲龙煞北
         * 正冲：丙辰(1976 2036)
         * 时宜：见贵 求财 嫁娶 进人口 移徙 安葬
         * 时忌：祭祀 祈福 斋醮 开光 赴任 出行
         * 吉神：右弼
         * 凶煞：路空 天刑
         * 财喜：财神正南 喜神正南
         * <p>
         * zhiri : 天牢(黑道日)
         * gongli : 公元 2017年5月1日 星期一
         * jsyq : 五富 四相 福生
         * jrhh : 今日与属龙属猴半三合，与属牛六合，与属马相冲，与属羊相害，与属兔相刑。
         * ji : 开工 安葬
         * jieri : 公历节日:  国际劳动节
         * t5 :
         * 吉凶：吉
         * 时柱：乙卯(大溪水)
         * 冲煞：冲鸡煞西
         * 正冲：己酉(1969 2029)
         * 时宜：求财 见贵 祭祀 祈福 酬神 修造 盖屋 移徙 安床 入宅 开市 开仓
         * 时忌：赴任 出行
         * 吉神：天官 玉堂 少微
         * 凶煞：日刑
         * 财喜：财神东南 喜神西北
         * <p>
         * t7 :
         * 吉凶：凶
         * 时柱：丙辰(沙中土)
         * 冲煞：冲狗煞南
         * 正冲：庚戌(1970 2030)
         * 时宜：祈福 求嗣 订婚 嫁娶 出行 求财 开市 交易 安床 祭祀
         * 时忌：上梁 盖屋 入殓
         * 吉神：三合 喜神 武曲
         * 凶煞：天兵
         * 财喜：财神正西 喜神西南
         * <p>
         * t9 :
         * 吉凶：凶
         * 时柱：丁巳(沙中土)
         * 冲煞：冲猪煞东
         * 正冲：辛亥(1971 2031)
         * 时宜：入宅 赴任 出行 求财 见贵 订婚 嫁娶
         * 时忌：开光 修造 安葬
         * 吉神：天赦 日禄 五符
         * 凶煞：大退
         * 财喜：财神正西 喜神正南
         * <p>
         * chongsha : 冲马[正冲壬午]煞南
         * yi : 订盟 订婚 出行 祭祀 祈福 装修 动土 搬家 入宅
         */

        private String t3;
        private String t21;
        private String t1;
        private String t23;
        private String nongli;
        private String jieqi24;
        private String nayin;
        private String tszf;
        private String shengxiao;
        private String xingzuo;
        private String ganzhi;
        private String t11;
        private String t13;
        private String t15;
        private String sheng12;
        private String t17;
        private String pzbj;
        private String t19;
        private String zhiri;
        private String gongli;
        private String jsyq;
        private String jrhh;
        private String ji;
        private String jieri;
        private String t5;
        private String t7;
        private String t9;
        private String chongsha;
        private String yi;

        public String getT3() {
            return t3;
        }

        public void setT3(String t3) {
            this.t3 = t3;
        }

        public String getT21() {
            return t21;
        }

        public void setT21(String t21) {
            this.t21 = t21;
        }

        public String getT1() {
            return t1;
        }

        public void setT1(String t1) {
            this.t1 = t1;
        }

        public String getT23() {
            return t23;
        }

        public void setT23(String t23) {
            this.t23 = t23;
        }

        public String getNongli() {
            return nongli;
        }

        public void setNongli(String nongli) {
            this.nongli = nongli;
        }

        public String getJieqi24() {
            return jieqi24;
        }

        public void setJieqi24(String jieqi24) {
            this.jieqi24 = jieqi24;
        }

        public String getNayin() {
            return nayin;
        }

        public void setNayin(String nayin) {
            this.nayin = nayin;
        }

        public String getTszf() {
            return tszf;
        }

        public void setTszf(String tszf) {
            this.tszf = tszf;
        }

        public String getShengxiao() {
            return shengxiao;
        }

        public void setShengxiao(String shengxiao) {
            this.shengxiao = shengxiao;
        }

        public String getXingzuo() {
            return xingzuo;
        }

        public void setXingzuo(String xingzuo) {
            this.xingzuo = xingzuo;
        }

        public String getGanzhi() {
            return ganzhi;
        }

        public void setGanzhi(String ganzhi) {
            this.ganzhi = ganzhi;
        }

        public String getT11() {
            return t11;
        }

        public void setT11(String t11) {
            this.t11 = t11;
        }

        public String getT13() {
            return t13;
        }

        public void setT13(String t13) {
            this.t13 = t13;
        }

        public String getT15() {
            return t15;
        }

        public void setT15(String t15) {
            this.t15 = t15;
        }

        public String getSheng12() {
            return sheng12;
        }

        public void setSheng12(String sheng12) {
            this.sheng12 = sheng12;
        }

        public String getT17() {
            return t17;
        }

        public void setT17(String t17) {
            this.t17 = t17;
        }

        public String getPzbj() {
            return pzbj;
        }

        public void setPzbj(String pzbj) {
            this.pzbj = pzbj;
        }

        public String getT19() {
            return t19;
        }

        public void setT19(String t19) {
            this.t19 = t19;
        }

        public String getZhiri() {
            return zhiri;
        }

        public void setZhiri(String zhiri) {
            this.zhiri = zhiri;
        }

        public String getGongli() {
            return gongli;
        }

        public void setGongli(String gongli) {
            this.gongli = gongli;
        }

        public String getJsyq() {
            return jsyq;
        }

        public void setJsyq(String jsyq) {
            this.jsyq = jsyq;
        }

        public String getJrhh() {
            return jrhh;
        }

        public void setJrhh(String jrhh) {
            this.jrhh = jrhh;
        }

        public String getJi() {
            return ji;
        }

        public void setJi(String ji) {
            this.ji = ji;
        }

        public String getJieri() {
            return jieri;
        }

        public void setJieri(String jieri) {
            this.jieri = jieri;
        }

        public String getT5() {
            return t5;
        }

        public void setT5(String t5) {
            this.t5 = t5;
        }

        public String getT7() {
            return t7;
        }

        public void setT7(String t7) {
            this.t7 = t7;
        }

        public String getT9() {
            return t9;
        }

        public void setT9(String t9) {
            this.t9 = t9;
        }

        public String getChongsha() {
            return chongsha;
        }

        public void setChongsha(String chongsha) {
            this.chongsha = chongsha;
        }

        public String getYi() {
            return yi;
        }

        public void setYi(String yi) {
            this.yi = yi;
        }
    }
}
