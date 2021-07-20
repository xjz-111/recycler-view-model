package com.leslie.recylerviewmodel.test.mvp.vm2;

import androidx.annotation.NonNull;

import com.leslie.recylerviewmodel.test.data.Mvp;
import com.leslie.recylerviewmodel.test.mvp.base.BaseContract;

import java.util.List;

/**
 * 作者：xjzhao
 * 时间：2021-07-20 14:37
 */
public class Mvp2Presenter implements BaseContract.IPresenter<Mvp>{
    private BaseContract.IView<Mvp> iView;

    public Mvp2Presenter(@NonNull BaseContract.IView iView) {
        this.iView = iView;
    }

    @Override
    public void initDataState(Mvp m) {
        List<Mvp> list = iView.getList();
        if (null != list){
            for (Mvp mvp : list){
                mvp.setClick(true);
            }
        }
        iView.notifyDataSetChanged();
    }
}
