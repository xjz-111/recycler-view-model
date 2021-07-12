package com.leslie.recycler_view_model;

/**
 * 介绍：
 * 作者：xjzhao
 * 邮箱：mr.feeling.heart@gmail.com
 * 时间: 2017-05-18  13:31
 */

public interface OnNotifyDataSetChanged {
    void viewModelNotifyDataSetChanged(BaseCommonViewModel vm);

    void viewModelNotifyItemChanged(int position, BaseCommonViewModel vm);
}
