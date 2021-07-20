package com.leslie.recylerviewmodel.test.mvp.vm1;

import androidx.annotation.NonNull;

import com.leslie.recylerviewmodel.BR;
import com.leslie.recylerviewmodel.R;
import com.leslie.recylerviewmodel.databinding.MvpVm1Binding;
import com.leslie.recylerviewmodel.test.data.Mvp;
import com.leslie.recylerviewmodel.test.mvp.base.OnListener;
import com.leslie.recylerviewmodel.test.mvp.base.PresenterBaseViewModel;

/**
 * 作者：xjzhao
 * 时间：2021-07-20 13:56
 */
public class MvpVm1 extends PresenterBaseViewModel<Mvp, MvpVm1Binding> {


    public MvpVm1(OnListener<Mvp> onListener) {
        super(new Mvp("#000000", 0, 0, "#000000"), onListener);
        presenter = new Mvp1Presenter(this);
    }

    @Override
    public int getVariable(Mvp mvp, int position) {
        return BR.mvp1;
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.mvp_vm1;
    }

    @Override
    public void onClick(int position, @NonNull Mvp mvp) {
        super.onClick(position, mvp);
        if (null != onListener){
            onListener.click(position, mvp);
        }
    }
}
