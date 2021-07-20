package com.leslie.recylerviewmodel.test.mvp.base;

import androidx.databinding.ViewDataBinding;

import com.leslie.recycler_view_model.BaseCommonViewModel;

import java.util.List;

/**
 * 作者：xjzhao
 * 时间：2021-07-20 14:41
 */
public abstract class PresenterBaseViewModel<T, K extends ViewDataBinding> extends BaseCommonViewModel<T, K> implements BaseContract.IView<T> {
    protected BaseContract.IPresenter<T> presenter;
    protected OnListener<T> onListener;

    public PresenterBaseViewModel(List<T> list, OnListener<T> onListener) {
        super(list);
        this.onListener = onListener;
    }

    public PresenterBaseViewModel(T t, OnListener<T> onListener) {
        super(t);
        this.onListener = onListener;
    }

    public PresenterBaseViewModel(boolean isStartLoad, OnListener<T> onListener) {
        super(isStartLoad);
        this.onListener = onListener;
    }

    public PresenterBaseViewModel(OnListener<T> onListener) {
        this.onListener = onListener;
    }

    public BaseContract.IPresenter<T> getPresenter(){
        return presenter;
    }
}
