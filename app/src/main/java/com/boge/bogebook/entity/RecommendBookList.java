package com.boge.bogebook.entity;

import java.util.List;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/12
 * 推荐的书籍
 */

public class RecommendBookList {


    /**
     * booklists : [{"id":"57331505025ffaa06cb28852","title":"★星光书局 ★(更新中\u2026\u2026)","author":"人闲","desc":"☆准星（不好看），★一星（***），★★二星（尚可），★★★三星（好看），★★★★（经典） ★★★★★五星 (神藏)\u2026\u2026\u2026\u2026（持续更新中\u2026\u2026）\u2026\u2026\u2026\u2026\u2026好书妙音:《过站不停》杨坤、《Bon，Bon》Pitbull、《Now You See Me》周杰伦、《Rain》金艺林、《大雨将至》徐佳莹、《前世情人》周杰伦、《鞋子特大号》周杰伦、《伤心证明书》陈奕迅","bookCount":357,"cover":"/agent/http://image.cmfu.com/books/1659706/1659706.jpg","collectorCount":120581},{"id":"533d508ac6d8f1e448002b61","title":"就是好看","author":"暴雨","desc":"好看的","bookCount":147,"cover":"/agent/http://image.cmfu.com/books/173050/173050.jpg","collectorCount":43618},{"id":"533ea342b774950c04000189","title":"经典小说","author":"暴雨","desc":"不好看你丫抽死我","bookCount":115,"cover":"/agent/http://wap.cmread.com/r/cover_file/6506/356296506/20110318122555/cover180240.jpg","collectorCount":27126}]
     * ok : true
     */

    private boolean ok;
    /**
     * id : 57331505025ffaa06cb28852
     * title : ★星光书局 ★(更新中……)
     * author : 人闲
     * desc : ☆准星（不好看），★一星（***），★★二星（尚可），★★★三星（好看），★★★★（经典） ★★★★★五星 (神藏)…………（持续更新中……）……………好书妙音:《过站不停》杨坤、《Bon，Bon》Pitbull、《Now You See Me》周杰伦、《Rain》金艺林、《大雨将至》徐佳莹、《前世情人》周杰伦、《鞋子特大号》周杰伦、《伤心证明书》陈奕迅
     * bookCount : 357
     * cover : /agent/http://image.cmfu.com/books/1659706/1659706.jpg
     * collectorCount : 120581
     */

    private List<BooklistsBean> booklists;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<BooklistsBean> getBooklists() {
        return booklists;
    }

    public void setBooklists(List<BooklistsBean> booklists) {
        this.booklists = booklists;
    }

    public static class BooklistsBean {
        private String id;
        private String title;
        private String author;
        private String desc;
        private int bookCount;
        private String cover;
        private int collectorCount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getBookCount() {
            return bookCount;
        }

        public void setBookCount(int bookCount) {
            this.bookCount = bookCount;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public int getCollectorCount() {
            return collectorCount;
        }

        public void setCollectorCount(int collectorCount) {
            this.collectorCount = collectorCount;
        }
    }
}
