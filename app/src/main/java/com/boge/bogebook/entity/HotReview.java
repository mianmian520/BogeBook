package com.boge.bogebook.entity;

import java.util.List;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/12
 * 热门评论
 */

public class HotReview {


    /**
     * reviews : [{"_id":"530a26522852d5280e04c19c","author":{"_id":"52e5a805392f550630006194","avatar":"/avatar/f9/32/f9329ebd7a1a24ac0c5176faa0fd930a","nickname":"王道飝","type":"normal","lv":9,"gender":"male"},"content":"天蚕土豆的两本书！！！\n萧炎：我认识你，你是林动，放眼整本武动乾坤，也是凤毛麟角般的存在。\n林动：我也认识你，你是萧炎，一出手，就能震惊整本斗破苍穹\n萧炎：想当年，在乌坦城，我们萧家是三大势力之一\n林动：想当年，在青阳镇，我们林家是四大势力之一\n萧炎：想当年，在乌坦城，我得到了一枚戒指\n林动：想当年，在青阳镇，我得到了一枚符文石\n萧炎：我的戒指里有药老，他来历神秘\n林动：我的符文石里有貂爷，他来历也神秘\n萧炎：乌坦城有个拍卖行，我常在那里卖丹药\n林动：青阳镇有个地下交易所，我也在那里卖丹药\n萧炎：我有个妹妹叫熏儿，不是亲生的\n林动：我也有个妹妹叫青檀，也不是亲生的\n萧炎：我的红颜知己小医仙是厄难毒体，本来很受罪，控制了毒丹就厉害了\n林动：我的青梅竹马青檀是阴煞魔体，本来也很受罪，控制了阴丹也厉害了\n萧炎：我的目标是上云岚宗，击败纳兰嫣然\n林动：我的目标是去大炎林家，击败琳琅天\n萧炎：我还有个身份，是炼丹师\n林动：我也有个身份，是符师\n萧炎：我们炼丹师靠得是精神力\n林动：我们符师靠得也是精神力\n萧炎：我们那有座塔叫丹塔\n林动：我们那有座塔叫符塔\n萧炎：药老带我去修炼\n林动：貂爷陪我去修炼\n萧炎：我修炼的目的是去找异火\n林动：我修炼的目的是去找祖符\n萧炎：有了异火我就是强大的炼丹师，实力倍增\n林动：有了祖符我就是强大的符师，实力也倍增\n萧炎：我的第一次给了美杜莎\n林动：我的第一次给了绫清竹\n萧炎：我的第一次\u2026\u2026不是自愿的\u2026\u2026我失控了\n林动：我的第一次\u2026\u2026也不是自愿的\u2026\u2026我也失控了\n萧炎：完事之后，美杜莎要杀我，却又救了我\n林动：完事之后，绫清竹也要杀我，也又救了我\n萧炎：\u2026\u2026\n林动：\u2026\u2026\n萧炎：我是一个叫土豆的家伙写出来的\n林动：我也是一个叫土豆的家伙写出来的\n萧炎：如有雷同\n林动：实属巧合","rating":1,"title":"天蚕土豆的两本书！！！惊人的相似之处","helpful":{"no":4868,"total":42411,"yes":47279},"likeCount":5468,"state":"distillate","updated":"2016-10-12T06:37:24.950Z","created":"2014-02-23T16:48:18.179Z","commentCount":14596},{"_id":"53e246f999925a7a4a301c27","rating":1,"content":"土豆从斗破苍穹开始，名气一路扶摇直上，直到有今天的成就，我不知道今天有多少人在看 土豆的书，肯定不会少。但是我已经放弃了，如今他已经是大作家，每天靠过去的作品的订阅就有花不完的钱，写作的动力早就被锦衣玉食的生活给磨光了。\n\n  有哪个读者愿意读一个月拖欠几十章更新的作品呢，而且拖欠了从来不会还，一个靠读者走向成功的作者，现在完全不考虑读者。我从此不再看土豆的作品。","title":"就说说大主宰","author":{"_id":"538312a0a6240358160155f0","avatar":"/avatar/d5/f7/d5f76fe21df7223b86f243dbf9bb3499","nickname":"相守的孩子","type":"normal","lv":5,"gender":"male"},"helpful":{"yes":8706,"total":7610,"no":1096},"likeCount":1585,"state":"normal","updated":"2016-10-11T22:07:30.038Z","created":"2014-08-06T15:17:13.103Z","commentCount":1983}]
     * ok : true
     */

    private boolean ok;
    /**
     * _id : 530a26522852d5280e04c19c
     * author : {"_id":"52e5a805392f550630006194","avatar":"/avatar/f9/32/f9329ebd7a1a24ac0c5176faa0fd930a","nickname":"王道飝","type":"normal","lv":9,"gender":"male"}
     * content : 天蚕土豆的两本书！！！
     萧炎：我认识你，你是林动，放眼整本武动乾坤，也是凤毛麟角般的存在。
     林动：我也认识你，你是萧炎，一出手，就能震惊整本斗破苍穹
     萧炎：想当年，在乌坦城，我们萧家是三大势力之一
     林动：想当年，在青阳镇，我们林家是四大势力之一
     萧炎：想当年，在乌坦城，我得到了一枚戒指
     林动：想当年，在青阳镇，我得到了一枚符文石
     萧炎：我的戒指里有药老，他来历神秘
     林动：我的符文石里有貂爷，他来历也神秘
     萧炎：乌坦城有个拍卖行，我常在那里卖丹药
     林动：青阳镇有个地下交易所，我也在那里卖丹药
     萧炎：我有个妹妹叫熏儿，不是亲生的
     林动：我也有个妹妹叫青檀，也不是亲生的
     萧炎：我的红颜知己小医仙是厄难毒体，本来很受罪，控制了毒丹就厉害了
     林动：我的青梅竹马青檀是阴煞魔体，本来也很受罪，控制了阴丹也厉害了
     萧炎：我的目标是上云岚宗，击败纳兰嫣然
     林动：我的目标是去大炎林家，击败琳琅天
     萧炎：我还有个身份，是炼丹师
     林动：我也有个身份，是符师
     萧炎：我们炼丹师靠得是精神力
     林动：我们符师靠得也是精神力
     萧炎：我们那有座塔叫丹塔
     林动：我们那有座塔叫符塔
     萧炎：药老带我去修炼
     林动：貂爷陪我去修炼
     萧炎：我修炼的目的是去找异火
     林动：我修炼的目的是去找祖符
     萧炎：有了异火我就是强大的炼丹师，实力倍增
     林动：有了祖符我就是强大的符师，实力也倍增
     萧炎：我的第一次给了美杜莎
     林动：我的第一次给了绫清竹
     萧炎：我的第一次……不是自愿的……我失控了
     林动：我的第一次……也不是自愿的……我也失控了
     萧炎：完事之后，美杜莎要杀我，却又救了我
     林动：完事之后，绫清竹也要杀我，也又救了我
     萧炎：……
     林动：……
     萧炎：我是一个叫土豆的家伙写出来的
     林动：我也是一个叫土豆的家伙写出来的
     萧炎：如有雷同
     林动：实属巧合
     * rating : 1
     * title : 天蚕土豆的两本书！！！惊人的相似之处
     * helpful : {"no":4868,"total":42411,"yes":47279}
     * likeCount : 5468
     * state : distillate
     * updated : 2016-10-12T06:37:24.950Z
     * created : 2014-02-23T16:48:18.179Z
     * commentCount : 14596
     */

    private List<ReviewsBean> reviews;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<ReviewsBean> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewsBean> reviews) {
        this.reviews = reviews;
    }

    public static class ReviewsBean {
        private String _id;
        /**
         * _id : 52e5a805392f550630006194
         * avatar : /avatar/f9/32/f9329ebd7a1a24ac0c5176faa0fd930a
         * nickname : 王道飝
         * type : normal
         * lv : 9
         * gender : male
         */

        private AuthorBean author;
        private String content;
        private int rating;
        private String title;
        /**
         * no : 4868
         * total : 42411
         * yes : 47279
         */

        private HelpfulBean helpful;
        private int likeCount;
        private String state;
        private String updated;
        private String created;
        private int commentCount;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public AuthorBean getAuthor() {
            return author;
        }

        public void setAuthor(AuthorBean author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public HelpfulBean getHelpful() {
            return helpful;
        }

        public void setHelpful(HelpfulBean helpful) {
            this.helpful = helpful;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public static class AuthorBean {
            private String _id;
            private String avatar;
            private String nickname;
            private String type;
            private int lv;
            private String gender;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getLv() {
                return lv;
            }

            public void setLv(int lv) {
                this.lv = lv;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }
        }

        public static class HelpfulBean {
            private int no;
            private int total;
            private int yes;

            public int getNo() {
                return no;
            }

            public void setNo(int no) {
                this.no = no;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getYes() {
                return yes;
            }

            public void setYes(int yes) {
                this.yes = yes;
            }
        }
    }
}
