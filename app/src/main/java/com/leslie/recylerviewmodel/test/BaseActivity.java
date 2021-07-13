package com.leslie.recylerviewmodel.test;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.leslie.recycler_view_model.BaseCommonViewModel;
import com.leslie.recycler_view_model.CommonRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：xjzhao
 * 时间：2021-07-08 14:09
 */
public abstract class BaseActivity extends AppCompatActivity {
    private RelativeLayout parent;
    protected RecyclerView recyclerView;
    protected CommonRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parent = new RelativeLayout(this);

        addView1(parent);

        setContentView(parent, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        addRecyclerView(parent);

        addView2(parent);

        recyclerView.setItemViewCacheSize(4);
//        recyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        recyclerView.setLayoutManager(getLayoutManager());
        recyclerView.setAdapter(adapter = getAdapter());
        List<BaseCommonViewModel> vms = new ArrayList<>();
        initViewModels(vms);
        adapter.setViewModels(vms);
    }

    protected void addView1(@NonNull RelativeLayout parent){}

    protected void addRecyclerView(@NonNull RelativeLayout parent){
        recyclerView = new AutoLoadRecyclerView(this);
        parent.addView(recyclerView);
    }

    protected void addView2(@NonNull RelativeLayout parent){}

    protected CommonRecyclerViewAdapter getAdapter(){
        return new CommonRecyclerViewAdapter(this);
    }

    protected abstract void initViewModels(@NonNull List<BaseCommonViewModel> vms);


    protected abstract RecyclerView.LayoutManager getLayoutManager();
}
