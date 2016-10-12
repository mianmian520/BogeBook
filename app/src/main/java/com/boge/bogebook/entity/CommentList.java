package com.boge.bogebook.entity;

import java.util.List;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/12
 * 神评论
 */

public class CommentList {


    /**
     * comments : [{"_id":"53aa384e337e13a901c16f5a","content":"直到下了追书神奇才明白原来书评比小说好看多了。","author":{"_id":"53aa351805e5152e22730320","avatar":"/avatar/dd/1b/dd1b322d90054f4fe231c63bbc6fe9fc","nickname":"久伴 xJ","type":"normal","lv":2,"gender":"male"},"floor":614,"likeCount":12830,"created":"2014-06-25T02:47:42.085Z","replyTo":null},{"_id":"530a28d82852d5280e04d8cb","content":"嗯","author":{"_id":"527f05ce9fbe2a652209cd69","avatar":"/avatar/61/3f/613fd3bfb53a03d8bc237f345dc407db","nickname":"黑涩的眼眸","type":"normal","lv":9,"gender":"male"},"floor":2,"likeCount":761,"created":"2014-02-23T16:59:04.001Z","replyTo":null},{"_id":"57ea059d3ed033be1b307e3e","content":"到现在为止，世界上不得不承认的七个事实,请别吃惊。 01.你的脖子转不了两圈。 2.你数不了你有多少头发。 3.当你舌头伸出来的时候你不能用鼻子呼吸。 4.你正在做第三条。 5.当你在做第三条的时候，其实你觉得是可行的，但是你看起来会像条小狗一样。 6.你现在在笑，因为我把你整了。 7，被整到的请点个赞不许耍赖！","author":{"_id":"564f4f7f1264e6c844020842","avatar":"/avatar/7c/ae/7caec510b45fe987dae2b1a94749e432","nickname":"危险领域-战神","type":"normal","lv":8,"gender":"female"},"floor":13899,"likeCount":469,"created":"2016-09-27T05:37:33.732Z","replyTo":null},{"_id":"57d00a975eb5fd6d50867ae0","content":"小二，来杯寂寞。\u201d？\u201c对不起！客官，本店只剩下空虚了。\u201d？\u201c那来杯开心。\u201d？\u201c不好意思！客官，卖完了，要不在给您来杯平时您经常喝的孤独？\u201d？\u201c不用了，我都喝了五年那玩意了，今天想换个口味。那快乐也没有么？\u201d？\u201c有，不过是水货。\u201d？\u201c那真心有么？\u201d？\u201c昨天有个买虚情假意的，买一送一搭给他了。您也知道，这年头真心不值钱了，没办法只能当赠品了。\u201d？\u201c算了，那来份爱情！＂？\u201c客官，你别为难我了，你也知道市面上根本没这东西了，已经缺货好多年了，不过听说黑市上出现过这玩意，价格很高。\u201d？\u201c唉\u2026\u2026\u2026好多年没喝过那玩意了，都忘了是什么味道了。\u201d？\u201c客官，我劝您还是别喝那玩意，那玩意味道不错，但是容易给人留下后遗症。\u201d？\u201c你怎么知道？你喝过？\u201d？\u201c我宁愿喝三鹿也不愿意喝那玩意，我只是听别的客官说的。客官，不行你就来杯无情，最近这个卖的不错，很多客官都喜欢喝这个。\u201d？\u201c呵呵！是吗？前一阵子他们不是爱喝狼心狗肺么？怎么又换口味了。\u201d？\u201c最近市面流行这个。\u201d？\u201c哦！算了，我不好那口，情人有么？\u201d？\u201c有。\u201d？\u201c来一个，给我多加点诺言。\u201d？\u201c现在诺言都是假的。\u201d？\u201c那知己怎么卖？\u201d？\u201c这个可贵了，一缘一份。\u201d？\u201c","author":{"_id":"5630d0e4246da64b3f706db9","avatar":"/avatar/ff/88/ff885b5bcb5e58174cae1486b64b6069","nickname":"？？？？？？","type":"normal","lv":8,"gender":"female"},"floor":13240,"likeCount":291,"created":"2016-09-07T12:39:51.066Z","replyTo":null},{"_id":"56a62dc704ca3139371c3214","content":"1楼：确实     2楼：恩    3楼：唉，可不是  4楼：恩，是吧  5楼：这丫   另外546，547楼已经删了，不用翻下去找了。。我翻了4千多楼，足足半小时←_←","author":{"_id":"563d8fd4e16a310d13a678d6","avatar":"/avatar/0d/f3/0df3bf22215338fa73e771ed47a3ef36","nickname":"逆袭的风","type":"normal","lv":7,"gender":"female"},"floor":4807,"likeCount":210,"created":"2016-01-25T14:14:31.872Z","replyTo":null}]
     * ok : true
     */

    private boolean ok;
    /**
     * _id : 53aa384e337e13a901c16f5a
     * content : 直到下了追书神奇才明白原来书评比小说好看多了。
     * author : {"_id":"53aa351805e5152e22730320","avatar":"/avatar/dd/1b/dd1b322d90054f4fe231c63bbc6fe9fc","nickname":"久伴 xJ","type":"normal","lv":2,"gender":"male"}
     * floor : 614
     * likeCount : 12830
     * created : 2014-06-25T02:47:42.085Z
     * replyTo : null
     */

    private List<CommentsBean> comments;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }

    public static class CommentsBean {
        private String _id;
        private String content;
        /**
         * _id : 53aa351805e5152e22730320
         * avatar : /avatar/dd/1b/dd1b322d90054f4fe231c63bbc6fe9fc
         * nickname : 久伴 xJ
         * type : normal
         * lv : 2
         * gender : male
         */

        private AuthorBean author;
        private int floor;
        private int likeCount;
        private String created;
        private Object replyTo;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public AuthorBean getAuthor() {
            return author;
        }

        public void setAuthor(AuthorBean author) {
            this.author = author;
        }

        public int getFloor() {
            return floor;
        }

        public void setFloor(int floor) {
            this.floor = floor;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public Object getReplyTo() {
            return replyTo;
        }

        public void setReplyTo(Object replyTo) {
            this.replyTo = replyTo;
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
    }
}
