package com.nsx.cookbook.bean;


public class RobotAnswer {

    /**
     * status : 0
     * msg : ok
     * result : {"type":"标准回复","content":"深圳今天22℃~27℃阴 微风\r\n建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。","relquestion":""}
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
         * type : 标准回复
         * content : 深圳今天22℃~27℃阴 微风
         建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。
         * relquestion :
         */

        private String type;
        private String content;
        private String relquestion;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getRelquestion() {
            return relquestion;
        }

        public void setRelquestion(String relquestion) {
            this.relquestion = relquestion;
        }
    }
}
