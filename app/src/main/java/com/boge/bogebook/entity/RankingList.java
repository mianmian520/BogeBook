package com.boge.bogebook.entity;

import java.util.List;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/12
 * 排行榜
 */

public class RankingList {

    private boolean ok;

    private List<MaleBean> female;
    private List<MaleBean> male;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<MaleBean> getFemale() {
        return female;
    }

    public void setFemale(List<MaleBean> female) {
        this.female = female;
    }

    public List<MaleBean> getMale() {
        return male;
    }

    public void setMale(List<MaleBean> male) {
        this.male = male;
    }

    public static class MaleBean {
        private String _id;
        private String title;
        private String cover;
        private boolean collapse;
        private String monthRank;
        private String totalRank;

        public MaleBean(String title) {
            this.title = title;
        }

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

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public boolean isCollapse() {
            return collapse;
        }

        public void setCollapse(boolean collapse) {
            this.collapse = collapse;
        }

        public String getMonthRank() {
            return monthRank;
        }

        public void setMonthRank(String monthRank) {
            this.monthRank = monthRank;
        }

        public String getTotalRank() {
            return totalRank;
        }

        public void setTotalRank(String totalRank) {
            this.totalRank = totalRank;
        }

        @Override
        public String toString() {
            return "MaleBean{" +
                    "_id='" + _id + '\'' +
                    ", title='" + title + '\'' +
                    ", cover='" + cover + '\'' +
                    ", collapse=" + collapse +
                    ", monthRank='" + monthRank + '\'' +
                    ", totalRank='" + totalRank + '\'' +
                    '}';
        }
    }


    @Override
    public String toString() {
        return "RankingList{" +
                "ok=" + ok +
                ", female=" + female +
                ", male=" + male +
                '}';
    }
}
