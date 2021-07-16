package com.leslie.recycler_view_model;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * 介绍：封装Recyclerview的Adapter使用的ViewModel
 *      默认可提供占位，做到局部的刷新操作
 * 作者：xjzhao
 * 邮箱：mr.feeling.heart@gmail.com
 * 时间: 2017-03-07  13:03
 */

public abstract class BaseCommonViewModel<T, K extends ViewDataBinding> implements OnRecyclerViewItemClickListener<T>, OnRecyclerViewItemLongClickListener<T>, IComm {
    private CommonRecyclerViewAdapter adapter;
    private List<T> list = new ArrayList<>();
    private int normalViewType; //有数据时的ViewType，每种类型一个单独的type，具体为在列表中ViewModel的顺序index
    private int spaceViewType;  //无数据时的ViewType
    protected int currentViewId = -1;//所含View的id，没有设置的会在ViewHolder中自动设置
    protected Context context;
    private int startPosition;
    private OnNotifyDataSetChanged onNotifyDataSetChanged;
    private SparseArray<K> states = new SparseArray<>();

    public BaseCommonViewModel(List<T> list) {
        if (null != list){
            this.list.addAll(list);
        }
    }

    public BaseCommonViewModel(T t) {
        if (null != t) {
            list.add(t);
        }
    }

    public BaseCommonViewModel(boolean isStartLoad) {
        if (isStartLoad){
            request();
        }
    }


    public BaseCommonViewModel() {
    }


    public Context getContext() {
        return context;
    }

    /**
     * 绑定数据
     *
     * @param t
     * @param position 全局position，非positionInViewModel
     * @return
     */
    public abstract int getVariable(T t, int position);

    /**
     * 获取当前ViewModel对应的layoutId
     *
     * @return
     */
    public abstract int getItemLayoutId();


    /**
     * 返回默认占位数目
     * @return
     */
    public int getDefaultCount(){
        return 1;
    }


    public final T getItem(int position){
        if (position > -1 && getItemCount() > position){
            return list.get(position);
        }
        return null;
    }

    public BaseCommonViewModel<T, K> setList(List<T> list) {
        this.list.clear();
        if (null != list){
            this.list.addAll(list);
        }
        notifyDataSetChanged();
        return this;
    }

    public BaseCommonViewModel<T, K> addList(List<T> list) {
        if (null != list){
            this.list.addAll(list);
            notifyDataSetChanged();
        }
        return this;
    }

    public BaseCommonViewModel<T, K> addListWithClean(List<T> list) {
        this.list.clear();
        if (null != list){
            this.list.addAll(list);
        }
        notifyDataSetChanged();
        return this;
    }

    public BaseCommonViewModel<T, K> addItem(T t){
        if (null != t) {
            list.add(t);
            notifyDataSetChanged();
        }
        return this;
    }

    public BaseCommonViewModel<T, K> addItemWithClean(T t){
        this.list.clear();
        if (null != t){
            list.add(t);
        }
        notifyDataSetChanged();
        return this;
    }


    public BaseCommonViewModel<T, K> setItem(T t, int position){
        if (getItemCount() == 0 && position == getItemCount()){
            list.add(t);
            if(null!=adapter){
                adapter.notifyItemChanged(position);
            }
            return this;
        }
        if (position > -1 && getItemCount() > position) {
            list.set(position,t);
            if(null!=adapter){
                adapter.notifyItemChanged(position);
            }
        }

        return this;
    }

    public BaseCommonViewModel<T, K> cleanList() {
        this.list.clear();
        notifyDataSetChanged();
        return this;
    }

    public BaseCommonViewModel<T, K> remove(int position){
        if (position > -1 && getItemCount() > position) {
            list.remove(position);
            notifyDataSetChanged();
        }
        return this;
    }

    public BaseCommonViewModel<T, K> remove(T t){
        list.remove(t);
        notifyDataSetChanged();
        return this;
    }

    public BaseCommonViewModel<T, K> addItem(int position, T t){
        if (null != t) {
            list.add(position, t);
            notifyDataSetChanged();
        }
        return this;
    }

    public BaseCommonViewModel<T, K> setOnNotifyDataSetChanged(OnNotifyDataSetChanged changed){
        this.onNotifyDataSetChanged = changed;
        return this;
    }

    public final int getItemCount(){
        return list.size();
    }

    public final boolean isEmpty(){
        return getItemCount() == 0;
    }

    public final List<T> getList(){
//        List<T> copy = new ArrayList<>(list);
//        copy.removeAll(emptyList);
//        return copy;
        return list;
    }

    final int getCount(){
        return Math.max(getItemCount(), getDefaultCount());
    }

    @Override
    public boolean onLongClick(int position, @NonNull T t) {
        return false;
    }

    @Override
    public void onClick(int position, @NonNull T t) {

    }

    public final BaseCommonViewModel<T, K> setStartPosition(int position){
        this.startPosition = position;
        return this;
    }

    public int getStartPosition() {
        return startPosition;
    }


    BaseCommonViewModel<T, K> setAdapter(CommonRecyclerViewAdapter adapter) {
        if (null == this.adapter) this.adapter = adapter;
        return this;
    }

    int getViewType(int position) {
        return isNormalViewType(getItem(position)) ? normalViewType : spaceViewType;
    }

    protected boolean isNormalViewType(T t){
        if (null != t){
            if ((!(t instanceof Collection) && !(t instanceof Map) && !(t instanceof SparseArray))
                || ((t instanceof Collection && !((Collection) t).isEmpty()) || (t instanceof SparseArray && ((SparseArray) t).size() != 0) || (t instanceof Map && !((Map) t).isEmpty()))){
                return true;
            }
        }
        return false;
    }

    BaseCommonViewModel<T, K> setNormalViewType(int type) {
        this.normalViewType = type;
        spaceViewType = normalViewType * SPACE_VIEW_TYPE_SPEC;
        return this;
    }

    BaseCommonViewModel<T, K> setContext(Context context) {
        this.context = context;
        initWhitContext();
        return this;
    }

    protected void initWhitContext(){

    }

    /**
     * 创建ViewModel，不需要子类来处理
     * @param parent
     * @param viewType
     * @return
     */
    final BaseCommonRecyclerViewHolder onCreateViewHolder(@NonNull LayoutInflater inflate, ViewGroup parent, int viewType){
        if (spaceViewType == viewType){
            return new SpaceViewHolder(DataBindingUtil.inflate(inflate, R.layout.spcae_viewmodel, parent, false)).setViewModel(new SpaceViewModel());
        }

        // 给所有的view添加id，方便在ItemDecoration中判断当前屏幕中的View是否为所要设置的ViewModel
        ViewDataBinding binding = createBind(inflate,parent);
        View root = binding.getRoot();
        if (-1 == root.getId()){
            root.setId(currentViewId == -1 ? currentViewId = root.hashCode() : currentViewId);
        }else {
            currentViewId = root.getId();
        }
        return new BaseCommonRecyclerViewHolder(binding).setViewModel(this);
    }


    protected <D extends ViewDataBinding> D createBind(@NonNull LayoutInflater inflate, ViewGroup parent){
        return DataBindingUtil.inflate(inflate, getItemLayoutId(), parent, false);
    }

    /**
     * 绑定数据，子类也无需处理
     * @param binding
     * @param holder
     * @param position
     * @param positionInViewModel
     */
    final void onBindViewHolder(@NonNull K binding, @NonNull BaseCommonRecyclerViewHolder holder, final int position, final int positionInViewModel, List<Object> payloads) {

        final T item = getItem(positionInViewModel);

        View itemView = holder.getItemView();

        if (null != item){
            initView(binding, item, positionInViewModel, payloads);
            if (isControlVariable()) setVariable(binding, item, position);
            itemView.setTag(item);
        }

        if (isInvokeClick()) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != item) {
                        BaseCommonViewModel.this.onClick(positionInViewModel, item);
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    if (null != item) {
                        return BaseCommonViewModel.this.onLongClick(positionInViewModel, item);
                    }
                    return false;
                }
            });
        }
    }

    protected void setVariable(@NonNull ViewDataBinding binding, T item, final int position){
        int variable = getVariable(item, position);
        if (variable > 0) {
            binding.setVariable(getVariable(item, position), item);
            binding.executePendingBindings();
        }
    }

    /**
     * 是否使用databinding的setVariable来统一设置布局内的数据 - 默认开启
     *
     * 返回false就要同步实现 {@link #initView(ViewDataBinding, Object, int, List)} }
     *
     * @return
     */
    protected boolean isControlVariable(){
        return true;
    }

    protected boolean isInvokeClick(){
        return true;
    }

    protected void initView(@NonNull K binding, @NonNull T t, int position, List<Object> payloads){

    }

    public final int getSpanCountWeight(int position, int sc) {
        if (sc < 1) return 1;
        if (getSpanCount() < 1) throw  new RuntimeException("Stub! The span count must be greater than 1.");
        if (sc % getSpanCount() != 0) throw new RuntimeException("Stub! The maximum number of columns must be an integer multiple of the number.");
        int dataPosition = position - startPosition;
        int tempSpanCount = getItemCount() % getSpanCount();
        int tempPosition = getItemCount() - tempSpanCount;
        if(dataPosition >= tempPosition && tempSpanCount != 0){
            return sc / tempSpanCount;
        }
        return sc / getSpanCount();
    }

    public int getSpanCount(){
        return DEFAULT_SPAN_COUNT;
    }

    public CommonRecyclerViewAdapter getAdapter(){
        return adapter;
    }

    public RecyclerView getRecyclerView(){
        return null != adapter ? adapter.getRecyclerView() : null;
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {

    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {

    }

    public void onViewAttachedToWindow(@NonNull K binding, T t, int position) {
        states.put(position, binding);
    }

    public void onViewDetachedFromWindow(@NonNull K binding, T t, int position) {
        states.remove(position);
    }

    public final void onResume(){
        int size = states.size();
        for (int i = 0; i < size; i++){
            int key = states.keyAt(i);
            onVisibleItemResume(states.get(key), getItem(key), key);
        }

    }

    public final void onPause(){
        int size = states.size();
        for (int i = 0; i < size; i++){
            int key = states.keyAt(i);
            onVisibleItemPause(states.get(key), getItem(key), key);
        }
    }

    protected void onVisibleItemResume(@NonNull K binding, T t, int position){

    }

    protected void onVisibleItemPause(@NonNull K binding, T t, int position){

    }

    private List<T> emptyList = new ArrayList<>();

    /**
     * 全局刷新
     */
    public void notifyDataSetChanged() {
        list.removeAll(emptyList);
        emptyList.clear();
        int diff;
        if ((diff = getItemCount() % getSpanCount()) != 0){
            for (int i = 0; i < diff; i++){
                emptyList.add(null);
            }
            list.addAll(emptyList);
        }
        if (getDefaultCount() >= getItemCount() && getItemCount() > 0){
            notifyChanged();
        }else if (null != adapter){
            adapter.resetSize();

            // 解决瀑布流返回顶部的时候左右Item交换的问题
            RecyclerView.LayoutManager lm = adapter.getLayoutManager();
            if (lm instanceof StaggeredGridLayoutManager){
                adapter.notifyItemInserted(list.size());
            }else {
                adapter.notifyDataSetChanged();
            }

            if (null != onNotifyDataSetChanged){
                onNotifyDataSetChanged.viewModelNotifyDataSetChanged(this);
            }
        }
    }

    /**
     * 未达到设定占位Size，占位View还够用，局部刷新
     */
    void notifyChanged(){
        if (null != adapter){
            adapter.notifyDataSetChanged(this);
            if (null != onNotifyDataSetChanged){
                onNotifyDataSetChanged.viewModelNotifyDataSetChanged(this);
            }
        }
    }

    /**
     * 获取当前ViewModel对应的ItemDecoration
     * @return
     */
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return null;
    }


    /**
     * 根据位置判断是否是当前的ViewModel
     * @param position
     * @return
     */
    protected boolean isCurrentViewModelWithPosition(int position){
        return position >= startPosition && position < startPosition + getItemCount();
    }

    /**
     * 根据实际的ViewHolder中的View的id来判断类型
     * @param v
     * @return
     */
    protected boolean isCurrentViewModel(View v){
        return null != v && currentViewId == v.getId();
    }

    /**
     * 刷新单条数据
     * @param positionInViewModel  initView()中传递的position
     */
    public void notifyItemChanged(int positionInViewModel){
        int position = positionInViewModel + startPosition;
        if (null != adapter && position > -1 && position < adapter.getItemCount()){
            adapter.notifyItemChanged(position);
            if (null != onNotifyDataSetChanged){
                onNotifyDataSetChanged.viewModelNotifyItemChanged(position, this);
            }
        }
    }


    public void notifyItemChanged(int position, Object payload){
        if (null != adapter){
            adapter.notifyItemChanged(position, payload);
        }
    }

    /**
     * 获取当前屏幕显示的第一个position
     * @return
     */
    @Deprecated
    protected int getFirstPosition(@NonNull RecyclerView.LayoutManager layoutManager){
        if (layoutManager instanceof LinearLayoutManager){
            LinearLayoutManager lm = (LinearLayoutManager) layoutManager;
            return lm.findFirstVisibleItemPosition() + getStartPosition();
        }else if (layoutManager instanceof StaggeredGridLayoutManager){
            int[] items = new int[2];
            StaggeredGridLayoutManager sm = (StaggeredGridLayoutManager) layoutManager;
            sm.findFirstVisibleItemPositions(items);
            return items[0] + getStartPosition();
        }
        return -1;
    }


    /**
     * 获取当前屏幕显示的最后一个position
     * @return
     */
    @Deprecated
    protected int getLastPosition(@NonNull RecyclerView.LayoutManager layoutManager){
        if (layoutManager instanceof LinearLayoutManager){
            LinearLayoutManager lm = (LinearLayoutManager) layoutManager;
            return lm.findLastVisibleItemPosition() + getStartPosition();
        }else if (layoutManager instanceof StaggeredGridLayoutManager){
            int[] items = new int[2];
            StaggeredGridLayoutManager sm = (StaggeredGridLayoutManager) layoutManager;
            sm.findLastVisibleItemPositions(items);
            return items[0] + getStartPosition();
        }
        return -1;
    }

    /***********对于非MainViewModel的可以直接在ViewModel中内部加载处理数据***************/

    // 加载数据
    protected void request(){}





//    private Disposable disposable;
//
//    public void resetDisposable(Disposable d) {
//        disposable = d;
//    }
//
//    protected Flowable<IData<T>> getFlowable(){
//        return null;
//    }
//
//    public void request(){
//        request(null);
//    }
//
//    public void request(OnVmRequestListener l){
//        Flowable<IData<T>> flowable = getFlowable();
//        if (null != flowable){
//            request(flowable, l);
//        }
//    }
//
//    private void request(@NonNull Flowable<IData<T>> flowable, final OnVmRequestListener l){
//        disposable = flowable.delay(20, TimeUnit.MILLISECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(new OnResponse<T>() {
//
//                    @Override
//                    public boolean onSuccess(IData<T> data) {
//                        onLoadSuccess(data.getData());
//                        onLoadEnd(true, 0, "");
//                        if (null != l) l.onSuccess();
//                        return super.onSuccess(data);
//                    }
//
//                    @Override
//                    public void onFail(HttpException ex) {
//                        super.onFail(ex);
//                        onLoadFailed(ex.getErrorCode(), ex.getMessage(), ex.getMessage());
//                        onLoadEnd(false, ex.getErrorCode(), ex.getMessage());
//                        if (null != l) l.onFailure();
//                    }
//
//                });
//        resetDisposable(disposable);
//    }
//
//    /**
//     * 加载成功自动填充数据刷新页面
//     * @param data
//     */
//    protected void onLoadSuccess(T data){
//        if (null != data){
//            addItemWithClean(data);
//        }
//    }
//
//    protected void onLoadFailed(int code, String displayMessage, String errorMessage){
//
//    }
//
//
//    protected void onLoadEnd(boolean isSuccess, int code, String msg){
//
//    }
//
//    public void cancelRequest() {
//        if (null != disposable && !disposable.isDisposed()) {
//            disposable.dispose();
//        }
//    }


}
