package com.leslie.recylerviewmodel.test.mvp.vm1;

import androidx.annotation.NonNull;

import com.leslie.recylerviewmodel.test.data.Mvp;
import com.leslie.recylerviewmodel.test.mvp.base.BaseContract;

/**
 * 作者：xjzhao
 * 时间：2021-07-20 14:54
 */
public class Mvp1Presenter implements BaseContract.IPresenter<Mvp> {
    private BaseContract.IView<Mvp> iView;

    public Mvp1Presenter(@NonNull BaseContract.IView iView) {
        this.iView = iView;
    }

    @Override
    public void initDataState(Mvp mvp) {
        Mvp item = iView.getItem(0);
        if (null != item){
            item.setUrl(mvp.getTitle());
            item.setTitle(mvp.getTitle());
        }
        iView.notifyItemChanged(0);
    }
}
