package com.boge.bogebook.entity.support;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/17
 * 发现类
 */

public class FindBean {

    private String title;
    private int iconResId;

    public FindBean(String title, int iconResId) {
        this.title = title;
        this.iconResId = iconResId;
    }

    public FindBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    @Override
    public String toString() {
        return "FindBean{" +
                "title='" + title + '\'' +
                ", iconResId=" + iconResId +
                '}';
    }
}
