package com.leslie.recylerviewmodel.test.vmRequest;

import android.os.Handler;
import android.os.Looper;

import androidx.databinding.ViewDataBinding;

import com.leslie.recycler_view_model.BaseCommonViewModel;


/**
 * 作者：xjzhao
 * 时间：2021-07-16 19:09
 */
abstract class BaseVm<T, K extends ViewDataBinding> extends BaseCommonViewModel<T, K> {
    protected Handler handler;

    public BaseVm() {
        handler = new Handler(Looper.getMainLooper());
        request();
    }
}