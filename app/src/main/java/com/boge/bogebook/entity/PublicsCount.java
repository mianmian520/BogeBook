package com.boge.bogebook.entity;

import java.util.List;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/13
 * 帖子数量
 */

public class PublicsCount {


    /**
     * review : {"postCount":411652,"todayNewPost":404}
     * help : {"postCount":428759,"todayNewPost":328}
     * ramble : {"postCount":305218,"todayNewPost":74}
     * girl : {"postCount":60314,"todayNewPost":55}
     */

    private PublicsBean publics;
    /**
     * books : []
     * publics : {"review":{"postCount":411652,"todayNewPost":404},"help":{"postCount":428759,"todayNewPost":328},"ramble":{"postCount":305218,"todayNewPost":74},"girl":{"postCount":60314,"todayNewPost":55}}
     * ok : true
     */

    private boolean ok;
    private List<?> books;

    public PublicsBean getPublics() {
        return publics;
    }

    public void setPublics(PublicsBean publics) {
        this.publics = publics;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<?> getBooks() {
        return books;
    }

    public void setBooks(List<?> books) {
        this.books = books;
    }

    public static class PublicsBean {
        /**
         * postCount : 411652
         * todayNewPost : 404
         */

        private ReviewBean review;
        private ReviewBean help;
        private ReviewBean ramble;
        private ReviewBean girl;

        public ReviewBean getReview() {
            return review;
        }

        public void setReview(ReviewBean review) {
            this.review = review;
        }

        public ReviewBean getHelp() {
            return help;
        }

        public void setHelp(ReviewBean help) {
            this.help = help;
        }

        public ReviewBean getRamble() {
            return ramble;
        }

        public void setRamble(ReviewBean ramble) {
            this.ramble = ramble;
        }

        public ReviewBean getGirl() {
            return girl;
        }

        public void setGirl(ReviewBean girl) {
            this.girl = girl;
        }

        public static class ReviewBean {
            private int postCount;
            private int todayNewPost;

            public int getPostCount() {
                return postCount;
            }

            public void setPostCount(int postCount) {
                this.postCount = postCount;
            }

            public int getTodayNewPost() {
                return todayNewPost;
            }

            public void setTodayNewPost(int todayNewPost) {
                this.todayNewPost = todayNewPost;
            }
        }
    }
}
