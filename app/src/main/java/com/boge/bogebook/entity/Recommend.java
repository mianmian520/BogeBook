package com.boge.bogebook.entity;

import java.util.List;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/12
 * 性别推荐小说
 */

public class Recommend {


    private boolean ok;
    /**
     * _id : 510f60ec59762e9453000007
     * author : 带玉
     * cover : /agent/http://media.tadu.com/4/1/c/a/41ca78fca346497096861efc4f2b0136_250_200.jpg
     * shortIntro : 小混混围堵同班姐妹花？唐宇正义出手，课间竟遭两姐妹当场告白，该选谁？
     * title : 我的贴身校花
     * hasCp : true
     * latelyFollower : 96037
     * latelyFollowerBase : 0
     * minRetentionRatio : 0
     * retentionRatio : 36.88
     * updated : 2016-10-11T14:24:31.419Z
     * chaptersCount : 6692
     * lastChapter : VIP卷 6693实力
     */

    private List<RecommendBook> books;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<RecommendBook> getBooks() {
        return books;
    }

    public void setBooks(List<RecommendBook> books) {
        this.books = books;
    }

    public static class RecommendBook {
        private String _id;
        private String author;
        private String cover;
        private String shortIntro;
        private String title;
        private boolean hasCp;
        private int latelyFollower;
        private int latelyFollowerBase;
        private int minRetentionRatio;
        private double retentionRatio;
        private String updated;
        private int chaptersCount;
        private String lastChapter;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isHasCp() {
            return hasCp;
        }

        public void setHasCp(boolean hasCp) {
            this.hasCp = hasCp;
        }

        public int getLatelyFollower() {
            return latelyFollower;
        }

        public void setLatelyFollower(int latelyFollower) {
            this.latelyFollower = latelyFollower;
        }

        public int getLatelyFollowerBase() {
            return latelyFollowerBase;
        }

        public void setLatelyFollowerBase(int latelyFollowerBase) {
            this.latelyFollowerBase = latelyFollowerBase;
        }

        public int getMinRetentionRatio() {
            return minRetentionRatio;
        }

        public void setMinRetentionRatio(int minRetentionRatio) {
            this.minRetentionRatio = minRetentionRatio;
        }

        public double getRetentionRatio() {
            return retentionRatio;
        }

        public void setRetentionRatio(double retentionRatio) {
            this.retentionRatio = retentionRatio;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

        public int getChaptersCount() {
            return chaptersCount;
        }

        public void setChaptersCount(int chaptersCount) {
            this.chaptersCount = chaptersCount;
        }

        public String getLastChapter() {
            return lastChapter;
        }

        public void setLastChapter(String lastChapter) {
            this.lastChapter = lastChapter;
        }
    }
}
