package com.boge.bogebook.common;

import com.boge.bogebook.util.FileUtil;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/12
 */

public class Constant {

    public static final String BASE_URL = "http://api.zhuishushenqi.com";

    public static final String IMG_BASE_URL = "http://statics.zhuishushenqi.com";

    /**性别**/
    public static final String GENDER = "gender";

    public static final String MALE = "male";
    public static final String FEMALE = "female";
    public static final String PRESS = "press";

    /**排行榜id**/
    public static final String RANKINGID = "rankingId";
    public static final String PATH = "PATH" ;
    public static final String LOCAL = "local";
    public static final String TITLE = "title";

    public static final String SUMMARY = "summary";
    public static final String CHAPTERS = "chapters";

    public static final String DIRECTORY = "BogeBook";

    public static final String BASE_PATH = FileUtil.getSDCardPath();
//    public static final String
}
