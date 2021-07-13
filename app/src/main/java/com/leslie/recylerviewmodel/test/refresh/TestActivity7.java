package com.leslie.recylerviewmodel.test.refresh;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.leslie.recycler_view_model.BaseCommonViewModel;
import com.leslie.recylerviewmodel.test.AutoLoadRecyclerView;
import com.leslie.recylerviewmodel.test.BaseActivity;
import com.leslie.recylerviewmodel.test.Utils;

import java.util.List;

/**
 * 下拉刷新+翻页
 *
 * 作者：xjzhao
 * 时间：2021-07-13 10:13
 */
public class TestActivity7 extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, IRefresh {
    private SwipeRefreshLayout refreshLayout;
    private RefreshViewModel mainVm;
    private RefreshFooterVm loadMoreVm;
    private boolean isRefreshing;

    @Override
    protected void addView1(@NonNull RelativeLayout parent) {
        refreshLayout = new SwipeRefreshLayout(this);
        parent.addView(refreshLayout, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        refreshLayout.setOnRefreshListener(this);
        setRefreshEnable(true);
    }

    @Override
    protected void addRecyclerView(@NonNull RelativeLayout parent) {
        recyclerView = new AutoLoadRecyclerView(this);
        refreshLayout.addView(recyclerView);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //计算Recyclerview第一个item的位置是否可见
                int topRowVerticalPosition = recyclerView.getChildCount() == 0 ? 0 : recyclerView.getChildAt(0).getTop();

                //当第一个item不可见时，设置SwipeRefreshLayout不可用
                setRefreshEnable(topRowVerticalPosition >= 0);
            }
        });

        onLoad(true);
    }

    @Override
    protected void initViewModels(@NonNull List<BaseCommonViewModel> vms) {
//        vms.add(new FooterViewModel("### 这是Header ###"));
        vms.add(mainVm = new RefreshViewModel());
        vms.add(loadMoreVm = new RefreshFooterVm(this));
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(this, 2);
    }

    @Override
    public void onRefresh() {
        onLoad(true);
    }


    @Override
    public void onLoad(boolean isRefresh) {
        setRefreshing(isRefresh);
        setRefreshEnable(false);
        refreshLayout.postDelayed(() -> {
            if (isRefresh){
                mainVm.setList(Utils.getList3());
            }else {
                mainVm.addList(Utils.getList3());
            }
            setRefreshing(false);
            setRefreshEnable(true);
            initFooterViewModelData();
            onChangeFooterState(Footer.FOOTER_LOADING);
        }, 3000);
    }

    @Override
    public void setRefreshing(final boolean refreshing) {
        isRefreshing = refreshing;
        if (null != refreshLayout) {
            refreshLayout.post(() -> refreshLayout.setRefreshing(refreshing));
        }
    }

    @Override
    public boolean isRefreshing() {
        return isRefreshing;
    }

    @Override
    public void setRefreshEnable(boolean enable) {
        refreshLayout.setEnabled(enable);
    }

    @Override
    public void onChangeFooterState(int state) {
        loadMoreVm.setState(state);
    }

    @Override
    public void initFooterViewModelData() {
        if (loadMoreVm.isEmpty()) {
            loadMoreVm.initFooter();
        }
    }
}
