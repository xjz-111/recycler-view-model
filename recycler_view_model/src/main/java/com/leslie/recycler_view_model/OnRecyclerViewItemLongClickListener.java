package com.leslie.recycler_view_model;

/**
 * 介绍：RecyclerView的Item长按事件监听
 * 作者：xjzhao
 * 邮箱：mr.feeling.heart@gmail.com
 * 时间: 2016-03-23  下午2:42
 */
interface OnRecyclerViewItemLongClickListener<T> {
    boolean onLongClick(int position, T t);
}
