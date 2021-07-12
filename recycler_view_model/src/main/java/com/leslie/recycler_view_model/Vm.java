package com.leslie.recycler_view_model;

/**
 * 介绍：
 * 作者：xjzhao
 * 邮箱：mr.feeling.heart@gmail.com
 * 时间: 2017-03-10  13:43
 */

class Vm {
    private BaseCommonViewModel viewModel;
    private int startPosition;

    public Vm(BaseCommonViewModel viewModel, int startPosition) {
        this.viewModel = viewModel;
        this.startPosition = startPosition;
    }

    BaseCommonViewModel getViewModel() {
        return viewModel;
    }

    void setViewModel(BaseCommonViewModel viewModel) {
        this.viewModel = viewModel;
    }


    int getStartPosition() {
        return startPosition;
    }

    void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

}
