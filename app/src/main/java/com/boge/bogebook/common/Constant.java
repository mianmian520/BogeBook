package com.boge.bogebook.common;

import android.support.annotation.StringDef;

import com.boge.bogebook.util.FileUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

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

    public static final String BOOKLIST_ID = "booklist_id";

    /**排行榜id**/
    public static final String RANKINGID = "rankingId";
    public static final String PATH = "PATH" ;
    public static final String LOCAL = "local";
    public static final String TITLE = "title";

    public static final String SUMMARY = "summary";
    public static final String CHAPTERS = "chapters";

    public static final String DIRECTORY = "BogeBook";

    public static final String BASE_PATH = FileUtil.getSDCardPath();

    /*** 历史记录*/
    public static final String HISTORY = "history";

    /*** 书单标签*/
    public static final String BOOK_TAG = "tags";
    /***当前选中的标签*/
    public static final String CHOOSE_TAG = "tag";

    @StringDef({
        CateType.HOT,
            CateType.NEW,
            CateType.REPUTATION,
            CateType.MONTHLY,
            CateType.OVER
    })
    /**
     * 分类类型
     */
    @Retention(RetentionPolicy.SOURCE)
    public @interface CateType{
        String HOT = "hot";
        String NEW = "new";
        String REPUTATION = "reputation";
        String OVER = "over";
        String MONTHLY = "monthly";
    }
}
