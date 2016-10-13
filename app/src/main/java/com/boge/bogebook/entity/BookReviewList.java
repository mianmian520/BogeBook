package com.boge.bogebook.entity;

import java.util.List;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/13
 * 书评区帖子列表
 */

public class BookReviewList {


    /**
     * reviews : [{"_id":"57fe7009f8826b5373c720ac","title":"三体这本书最大的贡献","book":{"_id":"50c54ad08380e4f81500002a","cover":"/agent/http://gocache.3g.cn/bookimage/bookpic/34/221834/221834_210_280.jpg?v=20140124","title":"三体","site":"w3gsc","type":"khly"},"helpful":{"total":-11,"no":18,"yes":7},"likeCount":0,"state":"normal","updated":"2016-10-13T05:36:44.278Z","created":"2016-10-12T17:16:57.527Z"},{"_id":"57fef0e67b1c740c458e724c","title":"一点不好的阅读感受","book":{"_id":"52567a4b8515ae761f0019d9","cover":"/agent/http://static.zongheng.com/upload/cover/2014/09/1410319853868.jpg","site":"zhuishuvip","title":"仙路争锋","type":"wxxx"},"helpful":{"total":0,"no":1,"yes":1},"likeCount":0,"state":"normal","updated":"2016-10-13T05:19:31.074Z","created":"2016-10-13T02:26:46.673Z"},{"_id":"57fee868e6bd67380a5c4ff3","title":"一旦拿起就放不下手的最爱","book":{"_id":"53e713fe12f5b5a35445a750","title":"狼行成双","cover":"/agent/http://ww2.sinaimg.cn/mw690/c3806772jw1ei3e5hv9s0j205k07xjsg.jpg","site":"jjwxc","type":"xdyq"},"helpful":{"total":-2,"no":4,"yes":2},"likeCount":0,"state":"normal","updated":"2016-10-13T05:42:25.162Z","created":"2016-10-13T01:50:32.541Z"},{"_id":"57fe3b31b8b9488b40f1ece0","title":"为执而魔，因心而狂","book":{"_id":"52385133777d4d8455002666","cover":"/agent/http://image.cmfu.com/books/2939492/2939492.jpg","site":"zhuishuvip","title":"执魔","type":"wxxx"},"helpful":{"total":10,"yes":14,"no":4},"likeCount":1,"state":"normal","updated":"2016-10-13T05:49:46.740Z","created":"2016-10-12T13:31:29.718Z"},{"_id":"57fee4a4ed29e8ae49ff2f87","title":"好看，喜欢这类型的文","book":{"_id":"535cc8e6563527574700bd1e","title":"金玉满唐","cover":"/agent/http://image.cmfu.com/books/2137787/2137787.jpg","site":"zhuishuvip","type":"gdyq"},"helpful":{"total":6,"yes":6,"no":0},"likeCount":2,"state":"normal","updated":"2016-10-13T01:42:00.739Z","created":"2016-10-13T01:34:28.509Z"},{"_id":"57fecfe7f258f0611f8e1898","title":"再次看别人推荐的都市文","book":{"_id":"531204297d3f734770014b55","cover":"/agent/http://image.cmfu.com/books/3056070/3056070.jpg","title":"佣兵的战争","site":"zhuishuvip","type":"dsyn"},"helpful":{"total":2,"no":2,"yes":4},"likeCount":0,"state":"normal","updated":"2016-10-13T04:53:54.665Z","created":"2016-10-13T00:05:59.647Z"},{"_id":"57fe46dfc8857a135f03d007","title":"比较一般的末世文，书荒能看下去吧","book":{"_id":"531bbec4a5badb8d2a0356a9","cover":"/agent/http://s7.static.jjwxc.net/frontcover/001/434/1434187.jpg","title":"末世之重生","site":"jjwxc","type":"xhqh"},"helpful":{"total":-3,"no":3,"yes":0},"likeCount":1,"state":"normal","updated":"2016-10-12T14:21:19.649Z","created":"2016-10-12T14:21:19.649Z"},{"_id":"57fe3b3ab80c6a7b6f008c05","title":"关于人物","book":{"_id":"5470b54b0772d58765501002","title":"拒嫁豪门：少夫人99次出逃","cover":"/agent/http://wfqqreader.3g.qq.com/cover/614/225614/t5_225614.jpg","site":"zhuishuvip","type":"qt"},"helpful":{"total":1,"yes":2,"no":1},"likeCount":0,"state":"normal","updated":"2016-10-12T13:31:38.937Z","created":"2016-10-12T13:31:38.937Z"},{"_id":"57fed7e565477b8f518b44f1","title":"看了之后我想出来的，毁尸灭迹的方法","book":{"_id":"565f25916ffc04320d481356","title":"达芬奇密码","cover":"/agent/http://bj.bs.baidu.com/wise-novel-authority-logo/911f8d7aa830181dea3902d378eb5ed9.jpg","site":"abaidu","type":"qt"},"helpful":{"total":17,"yes":17,"no":0},"likeCount":2,"state":"normal","updated":"2016-10-13T05:36:06.438Z","created":"2016-10-13T00:40:05.783Z"},{"_id":"57fecaf002106c103e0ab207","title":"还没看，抢个坑","book":{"_id":"57e91c55cf4f1083229ce865","title":"在日本渔村的日子","cover":"/agent/http://qidian.qpic.cn/qdbimg/349573/1004084607/180","site":"qidian","type":"qt"},"helpful":{"total":5,"yes":9,"no":4},"likeCount":0,"state":"normal","updated":"2016-10-13T05:42:40.224Z","created":"2016-10-12T23:44:48.791Z"},{"_id":"57fe2b1efb90ec0a613894a7","title":"大家好，我是作者。","book":{"_id":"57a7870c75d0c2b71796341f","title":"夜如雨下","cover":"/agent/http://qidian.qpic.cn/qdbimg/349573/3633877/180","site":"qidian","type":"qt"},"helpful":{"total":17,"yes":19,"no":2},"likeCount":1,"state":"normal","updated":"2016-10-13T03:54:45.642Z","created":"2016-10-12T12:22:54.007Z"},{"_id":"57fdb920e17e2e497280ac57","title":"《那片星空，那片海》","book":{"_id":"561f9429f044c9b646b3d03e","title":"那片星空，那片海","cover":"/agent/http://wap.cmread.com/r/cover_file/6901/407706901/20150615161639/cover180240.jpg","site":"my176","type":"qt"},"helpful":{"total":5,"yes":7,"no":2},"likeCount":2,"state":"normal","updated":"2016-10-13T03:58:51.357Z","created":"2016-10-12T04:16:32.637Z"},{"_id":"57ff1e7e1e48c56a5bb18941","title":"好书良心","book":{"_id":"579b25da41c3ea1e7c3703f3","title":"一言通天","cover":"/cover/147453005549140","site":"chuangshi","type":"qt"},"helpful":{"total":1,"yes":1,"no":0},"likeCount":0,"state":"normal","updated":"2016-10-13T05:41:18.627Z","created":"2016-10-13T05:41:18.627Z"},{"_id":"57ff1d83e29761b11265840a","title":"再没见过更烂的书","book":{"_id":"5562cefcdf257c5c18c731dc","title":"再世红夫人","cover":"/agent/http://img1.readnovel.com/incoming/book/0/4408/234408_ex.jpg","site":"readnovel","type":"qt"},"likeCount":0,"state":"normal","updated":"2016-10-13T05:37:07.500Z","created":"2016-10-13T05:37:07.500Z","helpful":{"yes":0,"no":0,"total":0}},{"_id":"57ff1b82fe3eb856204ec497","title":"繁星书评","book":{"_id":"57e4e7885ede1dbe28ce5353","title":"内功修仙路","cover":"/agent/http://qidian.qpic.cn/qdbimg/349573/1004081188/180","site":"qidian","type":"qt"},"likeCount":0,"state":"normal","updated":"2016-10-13T05:28:34.841Z","created":"2016-10-13T05:28:34.841Z","helpful":{"yes":0,"no":0,"total":0}},{"_id":"57ff1890fccd56d31e87520c","title":"万古至尊","book":{"_id":"526e8e3e7cfc087140004df7","cover":"/agent/http://image.cmfu.com/books/3347382/3347382.jpg","site":"zhuishuvip","title":"万古至尊","type":"xhqh"},"likeCount":0,"state":"normal","updated":"2016-10-13T05:16:00.139Z","created":"2016-10-13T05:16:00.139Z","helpful":{"yes":0,"no":0,"total":0}},{"_id":"57ff17c2c8cfbca25be1b0ba","title":"打发一下时间","book":{"_id":"5666ad2a5aeea6ef688cc55b","title":"铁血少将盛宠女军王","cover":"/agent/http://bj.bs.baidu.com/wise-novel-authority-logo/2554e37eebdbae2d85273e2bd6205b9b.jpg","site":"abaidu","type":"qt"},"likeCount":0,"state":"normal","updated":"2016-10-13T05:12:34.952Z","created":"2016-10-13T05:12:34.952Z","helpful":{"yes":0,"no":0,"total":0}},{"_id":"57fd78e3c6f2a83e18c996c6","title":"这本书好书就是更新太慢了，我已经看了三年多了，","book":{"_id":"542a5838a5ae10f815039a7f","title":"逆天邪神","cover":"/agent/http://static.zongheng.com/upload/cover/2014/11/1416425191645.jpg","site":"zhuishuvip","type":"xhqh"},"helpful":{"total":2,"no":13,"yes":15},"likeCount":3,"state":"normal","updated":"2016-10-13T04:59:40.745Z","created":"2016-10-11T23:42:27.977Z"},{"_id":"57ff1634cfb7438e2f4bd107","title":"为《儒》而谈","book":{"_id":"5373898f1032be0155019e73","title":"儒道至圣","cover":"/agent/http://image.cmfu.com/books/3173393/3173393.jpg","site":"zhuishuvip","type":"xhqh"},"helpful":{"total":1,"yes":1,"no":0},"likeCount":0,"state":"normal","updated":"2016-10-13T05:05:56.652Z","created":"2016-10-13T05:05:56.652Z"},{"_id":"57ff1250da443ceb30c17042","title":"汝可识得此阵？。。","book":{"_id":"57b8f506cb438924147df631","title":"万域霸主","cover":"/agent/http://img1.chuangshi.qq.com/chuangshi/p1/default-cover-large.png","site":"chuangshi","type":"qt"},"helpful":{"total":0,"no":1,"yes":1},"likeCount":0,"state":"normal","updated":"2016-10-13T05:08:25.551Z","created":"2016-10-13T04:49:20.661Z"}]
     * ok : true
     */

    private boolean ok;
    /**
     * _id : 57fe7009f8826b5373c720ac
     * title : 三体这本书最大的贡献
     * book : {"_id":"50c54ad08380e4f81500002a","cover":"/agent/http://gocache.3g.cn/bookimage/bookpic/34/221834/221834_210_280.jpg?v=20140124","title":"三体","site":"w3gsc","type":"khly"}
     * helpful : {"total":-11,"no":18,"yes":7}
     * likeCount : 0
     * state : normal
     * updated : 2016-10-13T05:36:44.278Z
     * created : 2016-10-12T17:16:57.527Z
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
        private String title;
        /**
         * _id : 50c54ad08380e4f81500002a
         * cover : /agent/http://gocache.3g.cn/bookimage/bookpic/34/221834/221834_210_280.jpg?v=20140124
         * title : 三体
         * site : w3gsc
         * type : khly
         */

        private BookBean book;
        /**
         * total : -11
         * no : 18
         * yes : 7
         */

        private HelpfulBean helpful;
        private int likeCount;
        private String state;
        private String updated;
        private String created;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public BookBean getBook() {
            return book;
        }

        public void setBook(BookBean book) {
            this.book = book;
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

        public static class BookBean {
            private String _id;
            private String cover;
            private String title;
            private String site;
            private String type;

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

            public String getSite() {
                return site;
            }

            public void setSite(String site) {
                this.site = site;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class HelpfulBean {
            private int total;
            private int no;
            private int yes;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getNo() {
                return no;
            }

            public void setNo(int no) {
                this.no = no;
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
