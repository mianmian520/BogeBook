package com.boge.bogebook.entity;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/31
 */

public class BookUpdateInfo {


    /**
     * _id : 510f60ec59762e9453000007
     * author : 带玉
     * referenceSource : sogou
     * updated : 2016-10-30T08:04:29.399Z
     * chaptersCount : 6773
     * lastChapter : VIP卷 6774以后
     */

    private String _id;
    private String author;
    private String referenceSource;
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

    public String getReferenceSource() {
        return referenceSource;
    }

    public void setReferenceSource(String referenceSource) {
        this.referenceSource = referenceSource;
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
