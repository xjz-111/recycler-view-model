package com.leslie.recycler_view_model;

/**
 * 介绍：
 * 作者：xjzhao
 * 邮箱：mr.feeling.heart@gmail.com
 * 时间: 2017-03-13  16:34
 */

class SpaceViewModel extends BaseCommonViewModel<String> {
    SpaceViewModel() {
        addItem("");
    }

    @Override
    public int getVariable(String s, int position) {
        return com.leslie.recycler_view_model.BR.space;
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.spcae_viewmodel;
    }

    @Override
    public int getDefaultCount() {
        return 1;
    }

    @Override
    public void onClick(int position, String s) {

    }
}
