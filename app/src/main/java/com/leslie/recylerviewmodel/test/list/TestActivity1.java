package com.leslie.recylerviewmodel.test.list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leslie.recycler_view_model.BaseCommonViewModel;
import com.leslie.recylerviewmodel.test.BaseActivity;
import com.leslie.recylerviewmodel.test.Utils;

import java.util.List;

/**
 * 最简单的列表
 *
 * 作者：xjzhao
 * 时间：2021-07-08 13:53
 */
public class TestActivity1 extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViewModels(@NonNull List<BaseCommonViewModel> vms) {
        vms.add(new ListViewModel(Utils.getList1()));
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }


}
