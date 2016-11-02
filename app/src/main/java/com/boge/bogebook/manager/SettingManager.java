package com.boge.bogebook.manager;

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


}
