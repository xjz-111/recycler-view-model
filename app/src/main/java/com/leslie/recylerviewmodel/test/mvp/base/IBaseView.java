package com.leslie.recylerviewmodel.test.mvp.base;

import java.util.List;

/**
 * 作者：xjzhao
 * 时间：2021-07-20 14:49
 */
interface IBaseView<T> {
    List<T> getList();

    T getItem(int position);

    void notifyDataSetChanged();

    void notifyItemChanged(int position);
}
