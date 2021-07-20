package com.leslie.recycler_view_model;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 介绍：基于DataBinding和ViewModel模型的Adapter，适用于RecyclerView中，可直接使用
 * 作者：xjzhao
 * 邮箱：mr.feeling.heart@gmail.com
 * 时间: 2017-03-07  10:28
 */

public class CommonRecyclerViewAdapter extends RecyclerView.Adapter<BaseCommonRecyclerViewHolder> implements IComm {

    protected Context context;
    private LayoutInflater inflate;
    private SparseArray<Vm> vmSparseArray;
    private Map<BaseCommonViewModel, RecyclerView.ItemDecoration> decorationMap;
    private int count;
    private BaseCommonViewModel[] vms;
    private RecyclerView recyclerView;
    private Handler handler = new Handler(Looper.getMainLooper());

    public CommonRecyclerViewAdapter(Context context) {
        init(context);
    }

    public CommonRecyclerViewAdapter(Context context, BaseCommonViewModel... vms) {
        init(context);
        setViewModels(vms);
    }

    private void init(Context context){
        vmSparseArray = new SparseArray<>();
        decorationMap = new HashMap<>();
        this.context = context;
        inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        setHasStableIds(true);
    }

    public void setViewModels(BaseCommonViewModel... vms) {
        this.vms = vms;
        resetSize();
        notifyDataSetChanged();

    }

    public void setViewModels(List<? extends BaseCommonViewModel> vms) {
        if (null == vms) vms = new ArrayList<>();
        this.vms = vms.toArray(new BaseCommonViewModel[vms.size()]);
        resetSize();
        notifyDataSetChanged();

    }

    public void addViewModels(BaseCommonViewModel... vms){
        if (null != vms && vms.length > 0){
            if (null != this.vms){
                List<BaseCommonViewModel> first = Arrays.asList(this.vms);
                List<BaseCommonViewModel> second = Arrays.asList(vms);
                List<BaseCommonViewModel> temp = new ArrayList<>();
                temp.addAll(first);
                temp.addAll(second);
                this.vms = temp.toArray(new BaseCommonViewModel[temp.size()]);
            }else {
                this.vms = vms;
            }
            resetSize();
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public BaseCommonRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getViewModel(viewType).onCreateViewHolder(inflate, parent, viewType);
    }

    @Override
    public void onBindViewHolder(BaseCommonRecyclerViewHolder holder, final int position) {
        bind(holder, position, null);
    }

    @Override
    public void onBindViewHolder(BaseCommonRecyclerViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        }else {
            bind(holder, position, payloads);
        }
    }

    /**
     * 适配单条刷新中的局部刷新
     * @param holder
     * @param position
     * @param payloads
     */
    final void bind(BaseCommonRecyclerViewHolder holder, int position, List<Object> payloads){
        ViewDataBinding binding = holder.getBinding();
        BaseCommonViewModel viewModel;
        if (null != binding && null != (viewModel = holder.getViewModel())) {
            final Vm vm = getVm(position);
            if (null != vm) {
                viewModel.onBindViewHolder(binding, holder, position, position - vm.getStartPosition(), payloads);
            }
        }
    }

    private Vm getVm(int position) {
        int size = vmSparseArray.size();
        int total = 0;
        int lastTotal = total;
        Vm vm;
        for (int i = 0; i < size; i++) {
            total += (vm = vmSparseArray.get(i)).getViewModel().getCount();
            if (position >= lastTotal && position < total) {
                return vm;
            }
            lastTotal = total;
        }
        return null;
    }


    private BaseCommonViewModel getViewModel(int viewType) {
        if (viewType < 0){
            viewType = viewType / SPACE_VIEW_TYPE_SPEC;
        }
        return vmSparseArray.get(viewType - 1).getViewModel();
    }

    @Override
    public int getItemViewType(int position) {
        Vm vm = getVm(position);
        return null != vm ? vm.getViewModel().getViewType(position - vm.getStartPosition()) : SPACE_VIEW_TYPE_SPEC;
    }

    @Override
    public int getItemCount() {
        return count;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    void resetSize(){
        vmSparseArray.clear();
        count = 0;
        int length;
        if (null != vms && (length = vms.length) > 0) {
            BaseCommonViewModel viewModel;
            for (int i = 0; i < length; i++) {
                if (null != (viewModel = vms[i])) {
                    viewModel.setAdapter(this).setNormalViewType(i + 1).setContext(context).setStartPosition(count);
                    vmSparseArray.put(i, new Vm(viewModel, count));
                    count += viewModel.getCount();
                    RecyclerView.ItemDecoration itemDecoration = decorationMap.get(viewModel);
                    if (null == itemDecoration && null != (itemDecoration = viewModel.getItemDecoration()) && null != recyclerView){
                        recyclerView.addItemDecoration(itemDecoration);
                        decorationMap.put(viewModel, itemDecoration);
                    }

                }
            }
        }
    }

    void notifyDataSetChanged(final BaseCommonViewModel viewModel){
        if (null != viewModel){
            final Vm vm = vmSparseArray.get(viewModel.getViewType(0) - 1);
            if (null != vm) {
                notifyItemRangeChanged(vm.getStartPosition(), viewModel.getCount());
            }
        }
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        setLayoutManager(recyclerView);
        resetSize();

        // Grid 中单列的情况
        RecyclerView.LayoutManager layoutManager;
        if (null != (layoutManager = recyclerView.getLayoutManager())){
            if (layoutManager instanceof GridLayoutManager) {
                final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
                    @Override
                    public int getSpanSize(int position) {
                        Vm vm = getVm(position);
                        BaseCommonViewModel viewModel;
                        if (null != vm && null != (viewModel = vm.getViewModel())){
                            return viewModel.getSpanCountWeight(position, gridLayoutManager.getSpanCount());
                        }
                        return 1;
                    }
                });
            }
        }
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);

    }

    @Override
    public void onViewAttachedToWindow(BaseCommonRecyclerViewHolder holder) {
        super.onViewAttachedToWindow(holder);

        BaseCommonViewModel viewModel = null;
        if (!(holder instanceof SpaceViewHolder) && null != (viewModel = holder.getViewModel())){
            int position = holder.getPosition() - viewModel.getStartPosition();
            viewModel.onViewAttachedToWindow(holder.getBinding(), viewModel.getItem(position), position);
        }

        //处理StaggeredGridLayoutManager 的单列item
        if(viewModel!=null){
            ViewGroup.LayoutParams layoutParams = holder.getItemView().getLayoutParams();
            if(layoutParams instanceof StaggeredGridLayoutManager.LayoutParams){
                StaggeredGridLayoutManager.LayoutParams l = (StaggeredGridLayoutManager.LayoutParams) layoutParams;
                l.setFullSpan(viewModel.getSpanCount() == 1);
            }
        }
    }

    @Override
    public void onViewDetachedFromWindow(BaseCommonRecyclerViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        BaseCommonViewModel viewModel = null;
        if (!(holder instanceof SpaceViewHolder) && null != (viewModel = holder.getViewModel())){
            int position = holder.getPosition() - viewModel.getStartPosition();
            viewModel.onViewDetachedFromWindow(holder.getBinding(), viewModel.getItem(position), position);
        }
    }

    private void setLayoutManager(RecyclerView recyclerView){
        this.recyclerView = recyclerView;
    }

    public boolean isEmpty(){
        int len;
        BaseCommonViewModel viewModel;
        if (null != vms && (len = vms.length) > 0){
            for (int i = 0; i < len; i++){
                if (null != (viewModel = vms[i]) && !viewModel.isEmpty()){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 根据列表中数据全局位置获取ViewModel
     * @param positionInRecyclerView
     * @return
     */
    public BaseCommonViewModel getViewModelByPosition(int positionInRecyclerView){
        if (positionInRecyclerView > -1 && positionInRecyclerView < getItemCount()){
            int size = vmSparseArray.size();
            BaseCommonViewModel viewModel;
            for (int i = 0; i < size; i++) {
                if (null != vmSparseArray.get(i) && null != (viewModel =  vmSparseArray.get(i).getViewModel()) && positionInRecyclerView < viewModel.getStartPosition() + viewModel.getItemCount()){
                    return viewModel;
                }
            }
        }
        return null;
    }

    /**
     * 一次性清空数据
     */
    public void clean(){
        int size = vmSparseArray.size();
        Vm vm;
        BaseCommonViewModel viewModel;
        for (int i = 0; i < size; i++) {
            if (null != (vm = vmSparseArray.get(i)) && null != (viewModel = vm.getViewModel())){
                viewModel.setList(null);
            }
        }
        notifyDataSetChanged();
    }

    /**
     * 根据添加的顺序获取ViewModel
     * @param index
     * @return
     */
    public BaseCommonViewModel getViewModelByAddPosition(int index){
        if (index > -1 && index < vmSparseArray.size()){
            int size = vmSparseArray.size();
            Vm vm;
//            BaseCommonViewModel viewModel;
            for (int i = 0; i < size; i++) {
                if (i == index && null != (vm = vmSparseArray.get(i))){
                    return vm.getViewModel();
                }
            }
        }
        return null;
    }


    /**
     * 获取所有的ViewModel
     * @return
     */
    public List<BaseCommonViewModel> getViewModels(){
        return null != vms && vms.length > 0 ? Arrays.asList(vms) : new ArrayList<BaseCommonViewModel>(0);
    }


    public RecyclerView.LayoutManager getLayoutManager(){
        return null != recyclerView ? recyclerView.getLayoutManager() : null;
    }

    public RecyclerView getRecyclerView(){
        return recyclerView;
    }

}
