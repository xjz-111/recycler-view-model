package com.leslie.recylerviewmodel.test.refresh;


/**
 * 介绍：
 * 作者：xjzhao
 * 邮箱：mr.feeling.heart@gmail.com
 * 时间: 2021-07-13  11:25
 */

interface IRefresh {

    /**
     * 加载数据
     * @param isRefresh 是否为刷新加载
     */
    void onLoad(boolean isRefresh);


    /**
     * 设置刷新
     * @param refreshing
     */
    void setRefreshing(final boolean refreshing);

    /**
     * 是否正在刷新
     * @return
     */
    boolean isRefreshing();

    void setRefreshEnable(boolean enable);

    void onChangeFooterState(int state);

    void initFooterViewModelData();
}
