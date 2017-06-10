package com.nsx.cookbook.bean;

import java.util.List;


public class FoodDetailBean {
    /**
     * status : 0
     * msg : ok
     * result : {"num":"1","list":[{"id":"7","classid":"2","name":"凉拌黑木耳","peoplenum":"1-2人","preparetime":"30分钟-1小时","cookingtime":"10-20分钟","content":"每个人做菜的方法和技巧是不同的，同样的菜蔬在每个人的手里做出来的味道都是不同的，且口感和味道相差很多。每个人菜具都有属性和性格，但更多的是风格。我的菜的属性就是家常的、简单的、自己爱吃的，仅此而已。当然有时候也是有点小小的想法和创意，但以失败居多，总能把简单的搞得复杂繁琐，但最终做出的菜只能是\u201c家常\u201d。说说木耳吧：木耳味甘、性平，具有益气、润肺、凉血、止血、涩肠、活血、养容等功效；木耳中的胶质可把残留在人体消化系统内的灰尘、杂质吸附集中起来排出体外，从而起到清胃涤肠的作用。特别适合缺铁的人士、矿工、冶金工人、纺织工、理发师食用。 我这里是矿区粉尘多，环境差，木耳可以常有。做个清淡点的，清新点的，那就用酱油、醋来做，拒绝辛辣。为了点缀还是放了点青红椒碎，不过这都是甜椒，不辣。","pic":"http://api.jisuapi.com/recipe/upload/20160719/115138_22062.jpg","tag":"减肥,家常菜,排毒,补铁","material":[{"mname":"凉拌醋","type":"0","amount":"适量"},{"mname":"盐","type":"0","amount":"适量"},{"mname":"酱油","type":"0","amount":"适量"},{"mname":"生姜","type":"0","amount":"适量"},{"mname":"青红椒","type":"0","amount":"适量"},{"mname":"花椒油","type":"0","amount":"适量"},{"mname":"食油","type":"0","amount":"适量"},{"mname":"白糖","type":"0","amount":"适量"},{"mname":"黑木耳","type":"1","amount":"20g"}],"process":[{"pcontent":"甜青红椒洗净切小丁，生姜切细丝。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162548_67766.jpg"},{"pcontent":"木耳泡发后，撕成小朵。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162548_90373.jpg"},{"pcontent":"把木耳下开水，快速焯水后捞出备用。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162548_58359.jpg"},{"pcontent":"把焯过水的木耳盛到干净盘子里，倒入欣和六月鲜酱油。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162549_56952.jpg"},{"pcontent":"倒入一汤匙凉拌醋。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162549_98601.jpg"},{"pcontent":"锅里倒少许食油，油热后放入青红椒爆香。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162549_41138.jpg"},{"pcontent":"放入一汤匙白糖。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162549_56724.jpg"},{"pcontent":"少许精盐，炒匀。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162550_28396.jpg"},{"pcontent":"把炒好的料汁倒在木耳上，拌匀。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162550_19014.jpg"},{"pcontent":"最后倒入少许的花椒油，拌匀即可。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162550_42470.jpg"}]}]}
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
         * num : 1
         * list : [{"id":"7","classid":"2","name":"凉拌黑木耳","peoplenum":"1-2人","preparetime":"30分钟-1小时","cookingtime":"10-20分钟","content":"每个人做菜的方法和技巧是不同的，同样的菜蔬在每个人的手里做出来的味道都是不同的，且口感和味道相差很多。每个人菜具都有属性和性格，但更多的是风格。我的菜的属性就是家常的、简单的、自己爱吃的，仅此而已。当然有时候也是有点小小的想法和创意，但以失败居多，总能把简单的搞得复杂繁琐，但最终做出的菜只能是\u201c家常\u201d。说说木耳吧：木耳味甘、性平，具有益气、润肺、凉血、止血、涩肠、活血、养容等功效；木耳中的胶质可把残留在人体消化系统内的灰尘、杂质吸附集中起来排出体外，从而起到清胃涤肠的作用。特别适合缺铁的人士、矿工、冶金工人、纺织工、理发师食用。 我这里是矿区粉尘多，环境差，木耳可以常有。做个清淡点的，清新点的，那就用酱油、醋来做，拒绝辛辣。为了点缀还是放了点青红椒碎，不过这都是甜椒，不辣。","pic":"http://api.jisuapi.com/recipe/upload/20160719/115138_22062.jpg","tag":"减肥,家常菜,排毒,补铁","material":[{"mname":"凉拌醋","type":"0","amount":"适量"},{"mname":"盐","type":"0","amount":"适量"},{"mname":"酱油","type":"0","amount":"适量"},{"mname":"生姜","type":"0","amount":"适量"},{"mname":"青红椒","type":"0","amount":"适量"},{"mname":"花椒油","type":"0","amount":"适量"},{"mname":"食油","type":"0","amount":"适量"},{"mname":"白糖","type":"0","amount":"适量"},{"mname":"黑木耳","type":"1","amount":"20g"}],"process":[{"pcontent":"甜青红椒洗净切小丁，生姜切细丝。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162548_67766.jpg"},{"pcontent":"木耳泡发后，撕成小朵。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162548_90373.jpg"},{"pcontent":"把木耳下开水，快速焯水后捞出备用。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162548_58359.jpg"},{"pcontent":"把焯过水的木耳盛到干净盘子里，倒入欣和六月鲜酱油。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162549_56952.jpg"},{"pcontent":"倒入一汤匙凉拌醋。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162549_98601.jpg"},{"pcontent":"锅里倒少许食油，油热后放入青红椒爆香。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162549_41138.jpg"},{"pcontent":"放入一汤匙白糖。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162549_56724.jpg"},{"pcontent":"少许精盐，炒匀。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162550_28396.jpg"},{"pcontent":"把炒好的料汁倒在木耳上，拌匀。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162550_19014.jpg"},{"pcontent":"最后倒入少许的花椒油，拌匀即可。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162550_42470.jpg"}]}]
         */

        private String num;
        private List<ListBean> list;

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 7
             * classid : 2
             * name : 凉拌黑木耳
             * peoplenum : 1-2人
             * preparetime : 30分钟-1小时
             * cookingtime : 10-20分钟
             * content : 每个人做菜的方法和技巧是不同的，同样的菜蔬在每个人的手里做出来的味道都是不同的，且口感和味道相差很多。每个人菜具都有属性和性格，但更多的是风格。我的菜的属性就是家常的、简单的、自己爱吃的，仅此而已。当然有时候也是有点小小的想法和创意，但以失败居多，总能把简单的搞得复杂繁琐，但最终做出的菜只能是“家常”。说说木耳吧：木耳味甘、性平，具有益气、润肺、凉血、止血、涩肠、活血、养容等功效；木耳中的胶质可把残留在人体消化系统内的灰尘、杂质吸附集中起来排出体外，从而起到清胃涤肠的作用。特别适合缺铁的人士、矿工、冶金工人、纺织工、理发师食用。 我这里是矿区粉尘多，环境差，木耳可以常有。做个清淡点的，清新点的，那就用酱油、醋来做，拒绝辛辣。为了点缀还是放了点青红椒碎，不过这都是甜椒，不辣。
             * pic : http://api.jisuapi.com/recipe/upload/20160719/115138_22062.jpg
             * tag : 减肥,家常菜,排毒,补铁
             * material : [{"mname":"凉拌醋","type":"0","amount":"适量"},{"mname":"盐","type":"0","amount":"适量"},{"mname":"酱油","type":"0","amount":"适量"},{"mname":"生姜","type":"0","amount":"适量"},{"mname":"青红椒","type":"0","amount":"适量"},{"mname":"花椒油","type":"0","amount":"适量"},{"mname":"食油","type":"0","amount":"适量"},{"mname":"白糖","type":"0","amount":"适量"},{"mname":"黑木耳","type":"1","amount":"20g"}]
             * process : [{"pcontent":"甜青红椒洗净切小丁，生姜切细丝。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162548_67766.jpg"},{"pcontent":"木耳泡发后，撕成小朵。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162548_90373.jpg"},{"pcontent":"把木耳下开水，快速焯水后捞出备用。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162548_58359.jpg"},{"pcontent":"把焯过水的木耳盛到干净盘子里，倒入欣和六月鲜酱油。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162549_56952.jpg"},{"pcontent":"倒入一汤匙凉拌醋。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162549_98601.jpg"},{"pcontent":"锅里倒少许食油，油热后放入青红椒爆香。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162549_41138.jpg"},{"pcontent":"放入一汤匙白糖。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162549_56724.jpg"},{"pcontent":"少许精盐，炒匀。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162550_28396.jpg"},{"pcontent":"把炒好的料汁倒在木耳上，拌匀。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162550_19014.jpg"},{"pcontent":"最后倒入少许的花椒油，拌匀即可。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162550_42470.jpg"}]
             */

            private String id;
            private String classid;
            private String name;
            private String peoplenum;
            private String preparetime;
            private String cookingtime;
            private String content;
            private String pic;
            private String tag;
            private List<MaterialBean> material;
            private List<ProcessBean> process;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getClassid() {
                return classid;
            }

            public void setClassid(String classid) {
                this.classid = classid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPeoplenum() {
                return peoplenum;
            }

            public void setPeoplenum(String peoplenum) {
                this.peoplenum = peoplenum;
            }

            public String getPreparetime() {
                return preparetime;
            }

            public void setPreparetime(String preparetime) {
                this.preparetime = preparetime;
            }

            public String getCookingtime() {
                return cookingtime;
            }

            public void setCookingtime(String cookingtime) {
                this.cookingtime = cookingtime;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public List<MaterialBean> getMaterial() {
                return material;
            }

            public void setMaterial(List<MaterialBean> material) {
                this.material = material;
            }

            public List<ProcessBean> getProcess() {
                return process;
            }

            public void setProcess(List<ProcessBean> process) {
                this.process = process;
            }

            public static class MaterialBean {
                /**
                 * mname : 凉拌醋
                 * type : 0
                 * amount : 适量
                 */

                private String mname;
                private String type;
                private String amount;

                public String getMname() {
                    return mname;
                }

                public void setMname(String mname) {
                    this.mname = mname;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getAmount() {
                    return amount;
                }

                public void setAmount(String amount) {
                    this.amount = amount;
                }
            }

            public static class ProcessBean {
                /**
                 * pcontent : 甜青红椒洗净切小丁，生姜切细丝。
                 * pic : http://api.jisuapi.com/recipe/upload/20160719/162548_67766.jpg
                 */

                private String pcontent;
                private String pic;

                public String getPcontent() {
                    return pcontent;
                }

                public void setPcontent(String pcontent) {
                    this.pcontent = pcontent;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }
            }
        }
    }
}
