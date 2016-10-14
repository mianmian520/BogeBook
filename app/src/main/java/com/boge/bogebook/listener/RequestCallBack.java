package com.boge.bogebook.listener;

/**
 * @author boge
 * @version 1.0
 * @date 2016/9/20
 */
public interface RequestCallBack<T> {

    void beforeRequest();

    /**
     * 数据加载成功，调用
     * @param data
     */
    void success(T data);

    /**
     * 出现异常是调用
     * @param errorMsg
     */
    void onError(String errorMsg);

}
