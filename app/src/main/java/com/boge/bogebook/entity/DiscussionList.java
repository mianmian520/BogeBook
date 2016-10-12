package com.boge.bogebook.entity;

import java.util.List;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/12
 * 书籍详情讨论列表
 */

public class DiscussionList {


    /**
     * posts : [{"_id":"57f9a3d01880f2965262db13","book":{"_id":"51d11e782de6405c45000068","cover":"/agent/http://image.cmfu.com/books/2750457/2750457.jpg","title":"大主宰"},"title":"那些土豆书中的金典语录","author":{"_id":"5546348576c02d3172757162","avatar":"/avatar/87/80/87808c4e65bd6e4d5a4c85eb31add9a6","nickname":"肖楠","type":"moderator","lv":8,"gender":"female"},"type":"normal","likeCount":137,"block":"book","state":"normal","updated":"2016-10-12T06:16:35.273Z","created":"2016-10-09T01:56:32.540Z","commentCount":116,"voteCount":0},{"_id":"57fcc0aaf64fe0003e129a17","book":{"_id":"51d11e782de6405c45000068","cover":"/agent/http://image.cmfu.com/books/2750457/2750457.jpg","title":"大主宰"},"title":"大家来说说要是黑光和墨银对牧尘出手，牧尘会把武祖叫来吗？","author":{"_id":"569c6d3df81080692e13a7fd","avatar":"/avatar/b3/30/b33093771206407e7934aa1010e45f7e","nickname":"正儿八经社会小青年i","type":"normal","lv":8,"gender":"male"},"type":"normal","likeCount":9,"block":"book","state":"normal","updated":"2016-10-12T07:08:52.344Z","created":"2016-10-11T10:36:26.374Z","commentCount":80,"voteCount":0},{"_id":"57fcf95ce59e8bfd7f52b6a4","book":{"_id":"51d11e782de6405c45000068","cover":"/agent/http://image.cmfu.com/books/2750457/2750457.jpg","title":"大主宰"},"title":"看看大主宰圈里面有11级的吗？","author":{"_id":"56c97e699d3ad20d03cdc48e","avatar":"/avatar/89/80/8980f7c3756dd1db133ddf8c7ccae737","nickname":"卍回☆忆\u203b","type":"normal","lv":7,"gender":"male"},"type":"normal","likeCount":2,"block":"book","state":"normal","updated":"2016-10-12T07:21:29.673Z","created":"2016-10-11T14:38:20.181Z","commentCount":48,"voteCount":0},{"_id":"57fd8eb6eec968402b040481","book":{"_id":"51d11e782de6405c45000068","cover":"/agent/http://image.cmfu.com/books/2750457/2750457.jpg","title":"大主宰"},"title":"土豆老读者们进来看看","author":{"_id":"551175a3765fd27e657650ee","avatar":"/avatar/63/62/6362a83c44cd0d719bb53fd0644a3478","nickname":"Code war criminals","type":"normal","lv":8,"gender":"male"},"type":"normal","likeCount":11,"block":"book","state":"normal","updated":"2016-10-12T07:11:43.213Z","created":"2016-10-12T01:15:34.891Z","commentCount":7,"voteCount":0},{"_id":"57fc891f8aeab6ba06d52ca9","book":{"_id":"51d11e782de6405c45000068","cover":"/agent/http://image.cmfu.com/books/2750457/2750457.jpg","title":"大主宰"},"title":"八部浮屠加一气化三清","author":{"_id":"56cdd69ae2c5f33630314b1a","avatar":"/avatar/72/47/72470961f2ff1e29fd842d66d15c703a","nickname":"晴空下的你","type":"normal","lv":7,"gender":"male"},"type":"normal","likeCount":23,"block":"book","state":"normal","updated":"2016-10-12T06:20:35.891Z","created":"2016-10-11T06:39:27.762Z","commentCount":40,"voteCount":0},{"_id":"57fc23e0f9eeeb0c47a2944b","book":{"_id":"51d11e782de6405c45000068","cover":"/agent/http://image.cmfu.com/books/2750457/2750457.jpg","title":"大主宰"},"title":"土豆小说出现最多的词句是?","author":{"_id":"56c1870de132b2e71476a07c","avatar":"/avatar/b1/00/b10092c4b5343d9cf01b10c093745801","nickname":"云兮君","type":"normal","lv":7,"gender":"male"},"type":"normal","likeCount":9,"block":"book","state":"normal","updated":"2016-10-12T06:26:46.029Z","created":"2016-10-10T23:27:28.381Z","commentCount":56,"voteCount":0},{"_id":"57fd68774999b2971ba78d1d","book":{"_id":"51d11e782de6405c45000068","cover":"/agent/http://image.cmfu.com/books/2750457/2750457.jpg","title":"大主宰"},"title":"切勿强行催动八部浮屠、看来马上要干天至尊了！","author":{"_id":"573aa78441d7a10f6b7a42f5","avatar":"/avatar/0e/1c/0e1c3df0204714e05b7fc5faa608b99d","nickname":"Ivan wu","type":"normal","lv":7,"gender":"male"},"type":"normal","likeCount":1,"block":"book","state":"normal","updated":"2016-10-12T07:11:11.815Z","created":"2016-10-11T22:32:23.019Z","commentCount":8,"voteCount":0},{"_id":"57fda06a5691635b37a5d9e7","book":{"_id":"51d11e782de6405c45000068","cover":"/agent/http://image.cmfu.com/books/2750457/2750457.jpg","title":"大主宰"},"title":"论牧尘出祭坛后如何面对两大天至尊！","author":{"_id":"57c82fb4e0089b8a70da8b3c","avatar":"/avatar/89/e0/89e0919ab24e43feba700a5e83830f89","nickname":"她似梦却是命i","type":"normal","lv":5,"gender":"male"},"type":"normal","likeCount":1,"block":"book","state":"normal","updated":"2016-10-12T06:21:31.403Z","created":"2016-10-12T02:31:06.058Z","commentCount":5,"voteCount":0},{"_id":"57fddc1f29500da120dba7d8","book":{"_id":"51d11e782de6405c45000068","cover":"/agent/http://image.cmfu.com/books/2750457/2750457.jpg","title":"大主宰"},"title":"哪个软件看书不要钱？","author":{"_id":"537d96bda5de976e600043d7","avatar":"/avatar/f7/80/f780063b1768507914996e5dad3ff33b","nickname":"～々","type":"normal","lv":9,"gender":"male"},"type":"normal","likeCount":0,"block":"book","state":"normal","updated":"2016-10-12T07:36:18.532Z","created":"2016-10-12T06:45:51.035Z","commentCount":3,"voteCount":0},{"_id":"57fdc0e917f14f212848da77","book":{"_id":"51d11e782de6405c45000068","cover":"/agent/http://image.cmfu.com/books/2750457/2750457.jpg","title":"大主宰"},"title":"养了好久了，现在写到哪了？","author":{"_id":"543cad22f6b9eb1b320649bd","avatar":"/avatar/5c/b5/5cb5d0e3399744fa122702849ecf5949","nickname":"哦","type":"normal","lv":8,"gender":"male"},"type":"normal","likeCount":0,"block":"book","state":"normal","updated":"2016-10-12T07:35:06.285Z","created":"2016-10-12T04:49:45.851Z","commentCount":5,"voteCount":0},{"_id":"57fc97e0adfb8f763bafa1ad","book":{"_id":"51d11e782de6405c45000068","cover":"/agent/http://image.cmfu.com/books/2750457/2750457.jpg","title":"大主宰"},"title":"你准备好接受传承了吗？","author":{"_id":"55ee5f15b94bc800245ef4e4","avatar":"/avatar/07/38/073845da06281a89bfca216910188214","nickname":"往事不苍凉","type":"normal","lv":8,"gender":"male"},"type":"normal","likeCount":16,"block":"book","state":"normal","updated":"2016-10-12T07:30:31.250Z","created":"2016-10-11T07:42:24.867Z","commentCount":18,"voteCount":0},{"_id":"57fcaface12e0395062fe78b","book":{"_id":"51d11e782de6405c45000068","cover":"/agent/http://image.cmfu.com/books/2750457/2750457.jpg","title":"大主宰"},"title":"牧尘被屠浮的两天至尊追时，应该会召唤林动","author":{"_id":"5752b5f8221157fc2314ecbd","avatar":"/avatar/2c/f1/2cf1f879812553ba4e13efd8e81035a3","nickname":"诸位请拔刀把、","type":"normal","lv":7,"gender":"male"},"type":"normal","likeCount":9,"block":"book","state":"normal","updated":"2016-10-11T16:51:19.364Z","created":"2016-10-11T09:23:56.928Z","commentCount":22,"voteCount":0},{"_id":"57fd90eb1cbbaf990db047de","book":{"_id":"51d11e782de6405c45000068","cover":"/agent/http://image.cmfu.com/books/2750457/2750457.jpg","title":"大主宰"},"title":"牧尘怎么摆脱浮屠古族两位天至尊的追杀","author":{"_id":"56f93c9fb4b52a4a14069556","avatar":"/avatar/9c/6e/9c6e1514d432947026b17c89abdd94d5","nickname":"搞你搞到一万年","type":"normal","lv":7,"gender":"male"},"type":"vote","likeCount":1,"block":"book","state":"normal","updated":"2016-10-12T07:34:09.507Z","created":"2016-10-12T01:24:59.666Z","commentCount":7,"voteCount":41},{"_id":"57fde66904605e2e4ee42c11","book":{"_id":"51d11e782de6405c45000068","cover":"/agent/http://image.cmfu.com/books/2750457/2750457.jpg","title":"大主宰"},"title":"土豆已死","author":{"_id":"56dcfcdd0beb139277014bfd","avatar":"/avatar/48/22/48220e823bee3b365e76affbd4735331","nickname":"單身ye不錯","type":"normal","lv":7,"gender":"male"},"type":"normal","likeCount":0,"block":"book","state":"normal","updated":"2016-10-12T07:29:45.958Z","created":"2016-10-12T07:29:45.958Z","commentCount":0,"voteCount":0},{"_id":"57fde45e7190f2da3ec69807","book":{"_id":"51d11e782de6405c45000068","cover":"/agent/http://image.cmfu.com/books/2750457/2750457.jpg","title":"大主宰"},"title":"更新的速度","author":{"_id":"5445664a7c81a887173713d1","avatar":"/avatar/1f/6c/1f6cee6f17554b8cd4a01e66dd000f73","nickname":"─╄OvЁ  小  橙  ☂☂","type":"normal","lv":8,"gender":"male"},"type":"normal","likeCount":0,"block":"book","state":"normal","updated":"2016-10-12T07:21:02.862Z","created":"2016-10-12T07:21:02.862Z","commentCount":0,"voteCount":0},{"_id":"57fdda10d14a688e024ab1f6","book":{"_id":"51d11e782de6405c45000068","cover":"/agent/http://image.cmfu.com/books/2750457/2750457.jpg","title":"大主宰"},"title":"各方道友 求推荐","author":{"_id":"57176c9ab10b766a4cf3634d","avatar":"/avatar/b6/1d/b61de831c6e7e38877ed89c170389e5c","nickname":"茕孑","type":"normal","lv":7,"gender":"male"},"type":"normal","likeCount":0,"block":"book","state":"normal","updated":"2016-10-12T06:37:04.265Z","created":"2016-10-12T06:37:04.265Z","commentCount":0,"voteCount":0},{"_id":"57fdd922f144d20d2403a1bd","book":{"_id":"51d11e782de6405c45000068","cover":"/agent/http://image.cmfu.com/books/2750457/2750457.jpg","title":"大主宰"},"title":"各方道友，还有其他好看小说没推荐一二。","author":{"_id":"57176c9ab10b766a4cf3634d","avatar":"/avatar/b6/1d/b61de831c6e7e38877ed89c170389e5c","nickname":"茕孑","type":"normal","lv":7,"gender":"male"},"type":"normal","likeCount":0,"block":"book","state":"normal","updated":"2016-10-12T06:33:06.972Z","created":"2016-10-12T06:33:06.972Z","commentCount":0,"voteCount":0},{"_id":"57fdd54f2b9650040ec56d88","book":{"_id":"51d11e782de6405c45000068","cover":"/agent/http://image.cmfu.com/books/2750457/2750457.jpg","title":"大主宰"},"title":"更新的好慢啊 两天一更也是醉了！","author":{"_id":"57ea2c7ccfbc10964fb366a9","avatar":"/avatar/74/99/7499f397c19b9bdaf8477bb1ab105729","nickname":"以前遇你","type":"normal","lv":4,"gender":"male"},"type":"normal","likeCount":3,"block":"book","state":"normal","updated":"2016-10-12T06:16:47.614Z","created":"2016-10-12T06:16:47.614Z","commentCount":0,"voteCount":0},{"_id":"57fdd4414c4eeb494573a948","book":{"_id":"51d11e782de6405c45000068","cover":"/agent/http://image.cmfu.com/books/2750457/2750457.jpg","title":"大主宰"},"title":"《大主宰》出来几年了，","author":{"_id":"57bb14e7d6ab38915f4c52b8","avatar":"/avatar/09/ec/09ec97d9114e72e3be809416ab0b06c1","nickname":"我的等级狠狠狠狠6 吧","type":"normal","lv":5,"gender":"male"},"type":"normal","likeCount":0,"block":"book","state":"normal","updated":"2016-10-12T06:12:17.057Z","created":"2016-10-12T06:12:17.057Z","commentCount":0,"voteCount":0},{"_id":"57fdd37a0419c2a134418602","book":{"_id":"51d11e782de6405c45000068","cover":"/agent/http://image.cmfu.com/books/2750457/2750457.jpg","title":"大主宰"},"title":"土豆的内心怎么想的","author":{"_id":"574d31b7a6d063bf6500423c","avatar":"/avatar/c5/29/c5291c50190575fa6019050d4e0b1f91","nickname":"弱水三千，尔知吾否","type":"normal","lv":6,"gender":"female"},"type":"normal","likeCount":1,"block":"book","state":"normal","updated":"2016-10-12T06:08:58.567Z","created":"2016-10-12T06:08:58.567Z","commentCount":0,"voteCount":0}]
     * ok : true
     */

    private boolean ok;
    /**
     * _id : 57f9a3d01880f2965262db13
     * book : {"_id":"51d11e782de6405c45000068","cover":"/agent/http://image.cmfu.com/books/2750457/2750457.jpg","title":"大主宰"}
     * title : 那些土豆书中的金典语录
     * author : {"_id":"5546348576c02d3172757162","avatar":"/avatar/87/80/87808c4e65bd6e4d5a4c85eb31add9a6","nickname":"肖楠","type":"moderator","lv":8,"gender":"female"}
     * type : normal
     * likeCount : 137
     * block : book
     * state : normal
     * updated : 2016-10-12T06:16:35.273Z
     * created : 2016-10-09T01:56:32.540Z
     * commentCount : 116
     * voteCount : 0
     */

    private List<PostsBean> posts;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<PostsBean> getPosts() {
        return posts;
    }

    public void setPosts(List<PostsBean> posts) {
        this.posts = posts;
    }

    public static class PostsBean {
        private String _id;
        /**
         * _id : 51d11e782de6405c45000068
         * cover : /agent/http://image.cmfu.com/books/2750457/2750457.jpg
         * title : 大主宰
         */

        private BookBean book;
        private String title;
        /**
         * _id : 5546348576c02d3172757162
         * avatar : /avatar/87/80/87808c4e65bd6e4d5a4c85eb31add9a6
         * nickname : 肖楠
         * type : moderator
         * lv : 8
         * gender : female
         */

        private AuthorBean author;
        private String type;
        private int likeCount;
        private String block;
        private String state;
        private String updated;
        private String created;
        private int commentCount;
        private int voteCount;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public BookBean getBook() {
            return book;
        }

        public void setBook(BookBean book) {
            this.book = book;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public AuthorBean getAuthor() {
            return author;
        }

        public void setAuthor(AuthorBean author) {
            this.author = author;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public String getBlock() {
            return block;
        }

        public void setBlock(String block) {
            this.block = block;
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

        public int getVoteCount() {
            return voteCount;
        }

        public void setVoteCount(int voteCount) {
            this.voteCount = voteCount;
        }

        public static class BookBean {
            private String _id;
            private String cover;
            private String title;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
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
