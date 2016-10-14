package com.boge.bogebook.entity;

import java.util.List;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/13
 */

public class AutoComplete {


    /**
     * keywords : ["tfboys之十二星座的守护","tfboys殿下的专宠萌物","tfboys之玫瑰的秘密","tfboys之凯皇溺宠","tfboys之追梦女孩","tfboys阳光之夏","tfboys先婚后爱","tfboys之追心不追星","tfboys我是你的谁","tfboys之棉花糖之恋"]
     * ok : true
     */

    private boolean ok;
    private List<String> keywords;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

}
