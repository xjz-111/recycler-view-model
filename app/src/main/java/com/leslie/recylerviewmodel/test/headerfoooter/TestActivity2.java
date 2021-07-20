package com.leslie.recylerviewmodel.test.headerfoooter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leslie.recycler_view_model.BaseCommonViewModel;
import com.leslie.recylerviewmodel.test.BaseActivity;
import com.leslie.recylerviewmodel.test.Utils;

import java.util.List;

/**
 * 带有header和Footer
 *
 * 作者：xjzhao
 * 时间：2021-07-09 00:28
 */
public class TestActivity2 extends BaseActivity {

    @Override
    protected void initViewModels(@NonNull List<BaseCommonViewModel> vms) {

        // Header
        HeaderViewModel headerViewModel = new HeaderViewModel(Utils.getList2());
        vms.add(headerViewModel);

        // Item
        ListViewMode2 vm = new ListViewMode2(Utils.getList1());
        vms.add(vm);

        // Footer
        vms.add(new FooterViewModel("######   这是Footer   ######"));


        // 测试刷新
        recyclerView.postDelayed(() -> {
            int updateIndex = 0;
            List<String> list = vm.getList();
            list.set(updateIndex, "更新数据了");
            adapter.notifyItemChanged(vm.getStartPosition() + updateIndex);
        }, 5000);
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }
}