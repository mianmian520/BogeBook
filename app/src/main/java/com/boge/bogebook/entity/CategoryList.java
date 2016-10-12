package com.boge.bogebook.entity;

import java.util.List;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/12
 * 分类列表
 */

public class CategoryList {


    /**
     * male : [{"name":"玄幻","bookCount":195408},{"name":"奇幻","bookCount":24528},{"name":"武侠","bookCount":15498},{"name":"仙侠","bookCount":60548},{"name":"都市","bookCount":129332},{"name":"职场","bookCount":7160},{"name":"历史","bookCount":30590},{"name":"军事","bookCount":6609},{"name":"游戏","bookCount":33892},{"name":"竞技","bookCount":3039},{"name":"科幻","bookCount":43793},{"name":"灵异","bookCount":22789},{"name":"同人","bookCount":34209},{"name":"轻小说","bookCount":3026}]
     * female : [{"name":"古代言情","bookCount":130990},{"name":"现代言情","bookCount":134772},{"name":"青春校园","bookCount":39615},{"name":"纯爱","bookCount":43261},{"name":"玄幻奇幻","bookCount":25752},{"name":"武侠仙侠","bookCount":15458},{"name":"科幻","bookCount":2118},{"name":"游戏竞技","bookCount":1771},{"name":"悬疑灵异","bookCount":2351},{"name":"同人","bookCount":48066},{"name":"女尊","bookCount":6340},{"name":"莉莉","bookCount":7847}]
     * press : [{"name":"传记名著","bookCount":812},{"name":"出版小说","bookCount":690},{"name":"人文社科","bookCount":2812},{"name":"生活时尚","bookCount":222},{"name":"经管理财","bookCount":1882},{"name":"青春言情","bookCount":724},{"name":"外文原版","bookCount":325},{"name":"政治军事","bookCount":38},{"name":"成功励志","bookCount":631},{"name":"育儿健康","bookCount":1008}]
     * ok : true
     */

    private boolean ok;
    /**
     * name : 玄幻
     * bookCount : 195408
     */

    private List<MaleBean> male;
    private List<MaleBean> female;
    private List<MaleBean> press;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<MaleBean> getMale() {
        return male;
    }

    public void setMale(List<MaleBean> male) {
        this.male = male;
    }

    public List<MaleBean> getFemale() {
        return female;
    }

    public void setFemale(List<MaleBean> female) {
        this.female = female;
    }

    public List<MaleBean> getPress() {
        return press;
    }

    public void setPress(List<MaleBean> press) {
        this.press = press;
    }

    public static class MaleBean {
        private String name;
        private int bookCount;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getBookCount() {
            return bookCount;
        }

        public void setBookCount(int bookCount) {
            this.bookCount = bookCount;
        }
    }
}
