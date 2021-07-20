package com.leslie.recylerviewmodel.test.mvp.base;

/**
 * 作者：xjzhao
 * 时间：2021-07-20 14:48
 */
public interface BaseContract {

    interface IPresenter<T> extends IBasePresenter<T> {

    }

    interface IView<T> extends IBaseView<T> {

    }
}
