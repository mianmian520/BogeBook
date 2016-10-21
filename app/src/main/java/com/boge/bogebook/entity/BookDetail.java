package com.boge.bogebook.entity;

import java.util.List;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/12
 * 小说详情
 */

public class BookDetail {


    /**
     * _id : 51d11e782de6405c45000068
     * author : 天蚕土豆
     * cover : /agent/http://image.cmfu.com/books/2750457/2750457.jpg
     * creater : iPhone 4S
     * longIntro : 大千世界，位面交汇，万族林立，群雄荟萃，一位位来自下位面的天之至尊，在这无尽世界，演绎着令人向往的传奇，追求着那主宰之路。
     无尽火域，炎帝执掌，万火焚苍穹。
     武境之内，武祖之威，震慑乾坤。
     西天之殿，百战之皇，战威无可敌。
     北荒之丘，万墓之地，不死之主镇天地。
     ......
     少年自北灵境而出，骑九幽冥雀，闯向了那精彩绝伦的纷纭世界，主宰之路，谁主沉浮？
     大千世界，万道争锋，吾为大主宰。
     * title : 大主宰
     * cat : 玄幻
     * majorCate : 玄幻
     * minorCate : 异界大陆
     * _le : false
     * allowMonthly : false
     * allowVoucher : true
     * hasCp : true
     * postCount : 135846
     * latelyFollower : 351344
     * latelyFollowerBase : 0
     * followerCount : 63161
     * wordCount : 4267518
     * serializeWordCount : 1635
     * minRetentionRatio : 0
     * retentionRatio : 41.12
     * updated : 2016-10-11T05:54:58.179Z
     * isSerial : true
     * chaptersCount : 1341
     * lastChapter : 第1382章 八部浮屠传承！
     * gender : ["male"]
     * tags : ["玄幻","热血","架空","异界大陆","巅峰","修炼","主宰"]
     * donate : false
     */

    private String _id;
    private String author;
    private String cover;
    private String creater;
    private String longIntro;
    private String title;
    private String cat;
    private String majorCate;
    private String minorCate;
    private boolean _le;
    private boolean allowMonthly;
    private boolean allowVoucher;
    private boolean hasCp;
    private int postCount;
    private int latelyFollower;
    private int latelyFollowerBase;
    private int followerCount;
    private int wordCount;
    private int serializeWordCount;
    private String minRetentionRatio;
    private String retentionRatio;
    private String updated;
    private boolean isSerial;
    private int chaptersCount;
    private String lastChapter;
    private boolean donate;
//    private List<String> gender;
    private List<String> tags;

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

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getLongIntro() {
        return longIntro;
    }

    public void setLongIntro(String longIntro) {
        this.longIntro = longIntro;
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

    public String getMajorCate() {
        return majorCate;
    }

    public void setMajorCate(String majorCate) {
        this.majorCate = majorCate;
    }

    public String getMinorCate() {
        return minorCate;
    }

    public void setMinorCate(String minorCate) {
        this.minorCate = minorCate;
    }

    public boolean is_le() {
        return _le;
    }

    public void set_le(boolean _le) {
        this._le = _le;
    }

    public boolean isAllowMonthly() {
        return allowMonthly;
    }

    public void setAllowMonthly(boolean allowMonthly) {
        this.allowMonthly = allowMonthly;
    }

    public boolean isAllowVoucher() {
        return allowVoucher;
    }

    public void setAllowVoucher(boolean allowVoucher) {
        this.allowVoucher = allowVoucher;
    }

    public boolean isHasCp() {
        return hasCp;
    }

    public void setHasCp(boolean hasCp) {
        this.hasCp = hasCp;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
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

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public int getSerializeWordCount() {
        return serializeWordCount;
    }

    public void setSerializeWordCount(int serializeWordCount) {
        this.serializeWordCount = serializeWordCount;
    }

    public String getMinRetentionRatio() {
        return minRetentionRatio;
    }

    public void setMinRetentionRatio(String minRetentionRatio) {
        this.minRetentionRatio = minRetentionRatio;
    }

    public String getRetentionRatio() {
        return retentionRatio;
    }

    public void setRetentionRatio(String retentionRatio) {
        this.retentionRatio = retentionRatio;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public boolean isIsSerial() {
        return isSerial;
    }

    public void setIsSerial(boolean isSerial) {
        this.isSerial = isSerial;
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

    public boolean isDonate() {
        return donate;
    }

    public void setDonate(boolean donate) {
        this.donate = donate;
    }

//    public List<String> getGender() {
//        return gender;
//    }
//
//    public void setGender(List<String> gender) {
//        this.gender = gender;
//    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "BookDetail{" +
                "_id='" + _id + '\'' +
                ", author='" + author + '\'' +
                ", cover='" + cover + '\'' +
                ", creater='" + creater + '\'' +
                ", longIntro='" + longIntro + '\'' +
                ", title='" + title + '\'' +
                ", cat='" + cat + '\'' +
                ", majorCate='" + majorCate + '\'' +
                ", minorCate='" + minorCate + '\'' +
                ", _le=" + _le +
                ", allowMonthly=" + allowMonthly +
                ", allowVoucher=" + allowVoucher +
                ", hasCp=" + hasCp +
                ", postCount=" + postCount +
                ", latelyFollower=" + latelyFollower +
                ", latelyFollowerBase=" + latelyFollowerBase +
                ", followerCount=" + followerCount +
                ", wordCount=" + wordCount +
                ", serializeWordCount=" + serializeWordCount +
                ", minRetentionRatio=" + minRetentionRatio +
                ", retentionRatio='" + retentionRatio + '\'' +
                ", updated='" + updated + '\'' +
                ", isSerial=" + isSerial +
                ", chaptersCount=" + chaptersCount +
                ", lastChapter='" + lastChapter + '\'' +
                ", donate=" + donate +
//                ", gender=" + gender +
                ", tags=" + tags +
                '}';
    }
}
