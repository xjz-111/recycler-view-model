package com.leslie.recylerviewmodel.test.vmRequest;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leslie.recycler_view_model.BaseCommonViewModel;
import com.leslie.recylerviewmodel.test.BaseActivity;

import java.util.List;

/**
 * 模拟各个模块自己管理网络请求
 *
 * 作者：xjzhao
 * 时间：2021-07-16 19:03
 */
public class TestActivity9 extends BaseActivity {
    @Override
    protected void initViewModels(@NonNull List<BaseCommonViewModel> vms) {
        vms.add(new Vm1());
        vms.add(new Vm2());
        vms.add(new Vm3());
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(this, 2);
    }
}
