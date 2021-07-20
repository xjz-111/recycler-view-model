package com.leslie.recylerviewmodel.test.mvp.base;

/**
 * 作者：xjzhao
 * 时间：2021-07-20 15:05
 */
public interface OnListener<T> {

    void click(int position, T t);
}
