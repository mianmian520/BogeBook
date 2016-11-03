package com.boge.bogebook.entity;

import java.util.List;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/13
 * 书籍查询列表
 */

public class SearchDetail {


    /**
     * books : [{"_id":"54ec5829f4ee6631468879bf","hasCp":false,"title":"tfboys之十二星座的守护","cat":"校园","author":"四叶草丛中的KQJ","site":"readnovel","cover":"/agent/http://img2.readnovel.com/incoming/book/0/860/310860_kw.jpg","shortIntro":"在这里给大家道个歉，由于太着急想写这部小说了，把王源的英文名写成q了，改不了，所以请大家多多包涵。如有雷同，纯属巧合。不喜勿喷哈~\\(≧▽≦)/~ 如果说我什么...","lastChapter":"单恋无终 后续 中","retentionRatio":8.34,"latelyFollower":411,"wordCount":192086},{"_id":"56643fbd2e25473b78b77f76","hasCp":false,"title":"tfboys之十二星座的守护","cat":"其它","author":"四叶草丛中的kqj","site":"abaidu","cover":"/agent/http://bj.bs.baidu.com/wise-novel-authority-logo/1fd116cdf80a79906484b26cfe21db15.jpg","shortIntro":"\"嘭\"她倒在血泊当中，海之星渐渐暗淡下来，直至成了一块废铜烂铁。这一刻，他才明白他对她的感情不止是兄妹情，他杀出重围，跑到被鲜血染红的女孩身旁，颤抖的双手托起女...","lastChapter":"104★一刀两段,从此反目成仇","retentionRatio":null,"latelyFollower":17,"wordCount":0}]
     * ok : true
     */

    private boolean ok;
    /**
     * _id : 54ec5829f4ee6631468879bf
     * hasCp : false
     * title : tfboys之十二星座的守护
     * cat : 校园
     * author : 四叶草丛中的KQJ
     * site : readnovel
     * cover : /agent/http://img2.readnovel.com/incoming/book/0/860/310860_kw.jpg
     * shortIntro : 在这里给大家道个歉，由于太着急想写这部小说了，把王源的英文名写成q了，改不了，所以请大家多多包涵。如有雷同，纯属巧合。不喜勿喷哈~\(≧▽≦)/~ 如果说我什么...
     * lastChapter : 单恋无终 后续 中
     * retentionRatio : 8.34
     * latelyFollower : 411
     * wordCount : 192086
     */

    private List<BooksBean> books;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<BooksBean> getBooks() {
        return books;
    }

    public void setBooks(List<BooksBean> books) {
        this.books = books;
    }

    public static class BooksBean {
        private String _id;
        private boolean hasCp;
        private String title;
        private String cat;
        private String author;
        private String site;
        private String cover;
        private String shortIntro;
        private String lastChapter;
        private String retentionRatio;
        private int latelyFollower;
        private int wordCount;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public boolean isHasCp() {
            return hasCp;
        }

        public void setHasCp(boolean hasCp) {
            this.hasCp = hasCp;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCat() {
            return cat;
        }

        public void setCat(String cat) {
            this.cat = cat;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getShortIntro() {
            return shortIntro;
        }

        public void setShortIntro(String shortIntro) {
            this.shortIntro = shortIntro;
        }

        public String getLastChapter() {
            return lastChapter;
        }

        public void setLastChapter(String lastChapter) {
            this.lastChapter = lastChapter;
        }

        public String getRetentionRatio() {
            return retentionRatio;
        }

        public void setRetentionRatio(String retentionRatio) {
            this.retentionRatio = retentionRatio;
        }

        public int getLatelyFollower() {
            return latelyFollower;
        }

        public void setLatelyFollower(int latelyFollower) {
            this.latelyFollower = latelyFollower;
        }

        public int getWordCount() {
            return wordCount;
        }

        public void setWordCount(int wordCount) {
            this.wordCount = wordCount;
        }
    }
}
