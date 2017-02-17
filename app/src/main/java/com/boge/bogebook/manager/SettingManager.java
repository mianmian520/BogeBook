package com.boge.bogebook.manager;

import com.boge.bogebook.BookApplication;
import com.boge.bogebook.util.ScreenUtils;
import com.boge.bogebook.util.SharedPreferencesUtil;

/**
 * 阅读设置
 * @author boge.
 * @date 2016/11/02.
 */
public class SettingManager {

    private volatile static SettingManager manager;

    public static SettingManager getInstance() {
        return manager != null ? manager : (manager = new SettingManager());
    }

    /**
     * 保存阅读进度
     * @param bookId  书籍id
     * @param currentChapter 章节
     * @param m_mbBufBeginPos 开始坐标
     * @param m_mbBufEndPos   结束坐标
     */
    public synchronized void saveReadProgress(String bookId, int currentChapter, int m_mbBufBeginPos, int m_mbBufEndPos) {
        SharedPreferencesUtil.getInstance()
                .putInt(getChapterKey(bookId), currentChapter)
                .putInt(getStartPosKey(bookId), m_mbBufBeginPos)
                .putInt(getEndPosKey(bookId), m_mbBufEndPos);
    }

    /**
     * 获取上次阅读章节及位置
     *
     * @param bookId
     * @return
     */
    public int[] getReadProgress(String bookId) {
        int lastChapter = SharedPreferencesUtil.getInstance().getInt(getChapterKey(bookId), 1);
        int startPos = SharedPreferencesUtil.getInstance().getInt(getStartPosKey(bookId), 0);
        int endPos = SharedPreferencesUtil.getInstance().getInt(getEndPosKey(bookId), 0);

        return new int[]{lastChapter, startPos, endPos};
    }

    public void removeReadProgress(String bookId) {
        SharedPreferencesUtil.getInstance()
                .remove(getChapterKey(bookId))
                .remove(getStartPosKey(bookId))
                .remove(getEndPosKey(bookId));
    }

    private String getChapterKey(String bookId) {
        return bookId + "-chapter";
    }

    private String getStartPosKey(String bookId) {
        return bookId + "-startPos";
    }

    private String getEndPosKey(String bookId) {
        return bookId + "-endPos";
    }

    /**
     * 得到阅读界面字体大小
     * @return
     */
    public int getReadFontSize() {
        return SharedPreferencesUtil.getInstance().getInt("font-size", ScreenUtils.dpToPxInt(16));
    }

    /**
     * 设置阅读界面字体大小
     * @param curFontSizeDp
     */
    public void saveReadFontSize(int curFontSizeDp) {
        SharedPreferencesUtil.getInstance().putInt("font-size", curFontSizeDp);
    }

    /**
     * 得到阅读界面屏幕亮度
     * @return
     */
    public int getReadBrightness() {
        return SharedPreferencesUtil.getInstance().getInt("readLightness",
                (int) ScreenUtils.getScreenBrightness(BookApplication.getmContext()));
    }

    /**
     * 保存阅读界面屏幕亮度
     *
     * @param percent 亮度比例 0~100
     */
    public void saveReadBrightness(int percent) {
        SharedPreferencesUtil.getInstance().putInt("readLightness", percent);
    }


    /**
     * 是否跟随系统亮度
     * @return
     */
    public boolean isAutoBrightness() {
        return SharedPreferencesUtil.getInstance().getBoolean("autoBrightness", false);
    }

    /**
     * 设置是否跟随系统亮度
     * @return
     */
    public void saveAutoBrightness(boolean enable) {
        SharedPreferencesUtil.getInstance().putBoolean("autoBrightness", enable);
    }

    /**
     * 是否可以使用音量键翻页
     * @return
     */
    public boolean isVolumeFlipEnable() {
        return SharedPreferencesUtil.getInstance().getBoolean("volumeFlip", true);
    }

    /**
     * 设置是否可以使用音量键翻页
     *
     * @param enable
     */
    public void saveVolumeFlipEnable(boolean enable) {
        SharedPreferencesUtil.getInstance().putBoolean("volumeFlip", enable);
    }

    /**
     * 得到阅读界面主题
     * @return
     */
    public int getReadTheme() {
        return SharedPreferencesUtil.getInstance().getInt("readtheme", 0);
    }

    /**
     * 保存阅读界面主题
     *
     * @param curTheme
     */
    public void saveReadTheme(int curTheme) {
        SharedPreferencesUtil.getInstance().putInt("readtheme", curTheme);
    }
}
