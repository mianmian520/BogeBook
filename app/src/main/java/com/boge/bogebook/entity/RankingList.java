package com.boge.bogebook.entity;

import android.os.Parcel;
import android.os.Parcelable;

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

    public static class MaleBean implements Parcelable {
        private String _id;
        private String title;
        private String cover;
        private boolean collapse;
        private String monthRank;
        private String totalRank;

        public MaleBean(String title) {
            this.title = title;
        }

        public MaleBean() {
        }

        public MaleBean(String _id, String title, boolean collapse) {
            this._id = _id;
            this.title = title;
            this.collapse = collapse;
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this._id);
            dest.writeString(this.title);
            dest.writeString(this.cover);
            dest.writeByte(this.collapse ? (byte) 1 : (byte) 0);
            dest.writeString(this.monthRank);
            dest.writeString(this.totalRank);
        }

        protected MaleBean(Parcel in) {
            this._id = in.readString();
            this.title = in.readString();
            this.cover = in.readString();
            this.collapse = in.readByte() != 0;
            this.monthRank = in.readString();
            this.totalRank = in.readString();
        }

        public static final Parcelable.Creator<MaleBean> CREATOR = new Parcelable.Creator<MaleBean>() {
            @Override
            public MaleBean createFromParcel(Parcel source) {
                return new MaleBean(source);
            }

            @Override
            public MaleBean[] newArray(int size) {
                return new MaleBean[size];
            }
        };
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
