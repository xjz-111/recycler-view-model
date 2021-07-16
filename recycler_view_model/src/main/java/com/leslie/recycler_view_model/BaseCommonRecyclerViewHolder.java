package com.leslie.recycler_view_model;

import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 介绍：
 * 作者：xjzhao
 * 邮箱：mr.feeling.heart@gmail.com
 * 时间: 2017-03-07  13:01
 */
public class BaseCommonRecyclerViewHolder<T extends ViewDataBinding, K> extends RecyclerView.ViewHolder {
    private View itemView;
    private T binding;
    private BaseCommonViewModel<K, T> viewModel;

    BaseCommonRecyclerViewHolder(T binding) {
        super(binding.getRoot());
        itemView = binding.getRoot();
        this.binding = binding;

    }

    T getBinding() {
        return binding;
    }

    public View getItemView() {
        return itemView;
    }

    public BaseCommonViewModel getViewModel() {
        return viewModel;
    }

    public BaseCommonRecyclerViewHolder setViewModel(BaseCommonViewModel<K, T> viewModel) {
        this.viewModel = viewModel;
        return this;
    }
}