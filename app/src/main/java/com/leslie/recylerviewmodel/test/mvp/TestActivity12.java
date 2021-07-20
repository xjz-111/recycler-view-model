package com.leslie.recylerviewmodel.test.mvp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leslie.recycler_view_model.BaseCommonViewModel;
import com.leslie.recylerviewmodel.test.BaseActivity;
import com.leslie.recylerviewmodel.test.Utils;
import com.leslie.recylerviewmodel.test.data.Mvp;
import com.leslie.recylerviewmodel.test.mvp.base.OnListener;
import com.leslie.recylerviewmodel.test.mvp.vm1.MvpVm1;
import com.leslie.recylerviewmodel.test.mvp.vm2.MvpVm2;

import java.util.List;

/**
 * 各个ViewModel模块有业务往来，解耦处理
 *
 * 作者：xjzhao
 * 时间：2021-07-20 13:53
 */
public class TestActivity12 extends BaseActivity {
    private MvpVm1 vm1;
    private MvpVm2 vm2;

    @Override
    protected void initViewModels(@NonNull List<BaseCommonViewModel> vms) {
        vms.add(vm1 = new MvpVm1(mvp1L));
        vms.add(vm2 = new MvpVm2(Utils.getList12(), mvp2L));
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }

    private OnListener<Mvp> mvp1L = (position, mvp) -> {
        vm2.getPresenter().initDataState(mvp);
    };

    private OnListener<Mvp> mvp2L = (position, mvp) -> {
        vm1.getPresenter().initDataState(mvp);
    };
}
