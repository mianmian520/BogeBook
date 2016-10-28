package com.boge.bogebook.bean;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/28
 */

public class LocalAndRecomendBook {

    private String name;
    private String path;
    private long size;
    private boolean isSelect = false;

    private String _id;
    private String cover;
    private String title;
    private boolean hasUp = false;
    private int chaptersCount;
    private String lastChapter;

    private boolean isLocal = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

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

    public boolean isHasUp() {
        return hasUp;
    }

    public void setHasUp(boolean hasUp) {
        this.hasUp = hasUp;
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

    public boolean isLocal() {
        return isLocal;
    }

    public void setLocal(boolean local) {
        isLocal = local;
    }

    @Override
    public String toString() {
        return "LocalAndRecomendBook{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", size=" + size +
                ", isSelect=" + isSelect +
                '}';
    }
}
