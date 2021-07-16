package com.leslie.recylerviewmodel.test.refresh;

import androidx.annotation.NonNull;

import com.leslie.recycler_view_model.BaseCommonViewModel;
import com.leslie.recylerviewmodel.R;
import com.leslie.recylerviewmodel.databinding.FooterVmBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 介绍：
 * 作者：xjzhao
 * 邮箱：mr.feeling.heart@gmail.com
 * 时间: 2021-07-13  10:57
 */

public class RefreshFooterVm extends BaseCommonViewModel<Footer, FooterVmBinding> {
    private IRefresh iLoad;
    private Footer footer;

    // FIXME: 修改bug，主ViewModel超过默认尺寸时会全局刷新，就会导致刷新被多次调用
    private boolean isRefreshUi = true;

    public RefreshFooterVm() {

    }

    public RefreshFooterVm(IRefresh iLoad) {
        setIRefresh(iLoad);
    }

    public void initFooter(){
        setData(new Footer());
    }


    private void setData(Footer data){
        footer = data;
        List<Footer> list = new ArrayList<>();
        list.add(data);
        setList(list);
    }


    public void setIRefresh(IRefresh iLoad) {
        this.iLoad = iLoad;
    }

    @Override
    public int getVariable(Footer footer, int position) {
        if (isRefreshUi && null != iLoad && !iLoad.isRefreshing() && null != footer && footer.getState() == IFooter.FOOTER_LOADING){
            iLoad.onLoad(false);
            isRefreshUi = false;
        }
        return com.leslie.recylerviewmodel.BR.footer;
    }


    @Override
    public int getItemLayoutId() {
        return R.layout.refresh_footer_vm;
    }

    @Override
    public int getDefaultCount() {
        return 1;
    }

    @Override
    public void onClick(int position, Footer footer) {
        if (Footer.FOOTER_FAILURED == footer.getState()){
            setState(Footer.FOOTER_LOADING);
        }
    }

    public void setState(final int state){
        if (null != footer) {
            isRefreshUi = true;
            footer.setState(state);
//            notifyDataSetChanged();
            notifyItemChanged(getStartPosition(), "hhh");
        }
    }

    @Override
    protected void initView(@NonNull FooterVmBinding binding, @NonNull Footer footer, int position, List<Object> payloads) {
        super.initView(binding, footer, position, payloads);
//        if (null != payloads && payloads.size() > 0) {
//            Log.i("xjzhao", "payloads: " + payloads.size() + "  -  " + payloads.get(0));
//        }
    }
}
