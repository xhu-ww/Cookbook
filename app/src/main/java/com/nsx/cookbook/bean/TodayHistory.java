package com.nsx.cookbook.bean;

import java.util.List;


public class TodayHistory {

    /**
     * status : 0
     * msg : ok
     * result : [{"title":"英格兰（包含威尔士）和苏格兰在大不列颠王国的名称下实行联合","year":"1707","month":"5","day":"1","content":"1707年,由于意识到更紧密的政治和经济联合会带来益处，双方同意建立一个单一的大不列颠议会。苏格兰仍然保留自己的司法制度和宗教社区。但是在乔治一世和乔治二世这两位汉诺威王朝新教徒统治期间,英格兰和苏格兰之间的关系紧张起来，詹姆斯二世党人发动过两次叛乱,试图恢复信奉天主教的斯图亚特王朝。<\/p>"}]
     */

    private String status;
    private String msg;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * title : 英格兰（包含威尔士）和苏格兰在大不列颠王国的名称下实行联合
         * year : 1707
         * month : 5
         * day : 1
         * content : 1707年,由于意识到更紧密的政治和经济联合会带来益处，双方同意建立一个单一的大不列颠议会。苏格兰仍然保留自己的司法制度和宗教社区。但是在乔治一世和乔治二世这两位汉诺威王朝新教徒统治期间,英格兰和苏格兰之间的关系紧张起来，詹姆斯二世党人发动过两次叛乱,试图恢复信奉天主教的斯图亚特王朝。</p>
         */

        private String title;
        private String year;
        private String month;
        private String day;
        private String content;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
