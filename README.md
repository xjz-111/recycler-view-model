#### recycler-view-model
```
RecyclerView的Item类型ViewModel化，高度解耦，每种样式一个Model，各个ViewModel使用自己的数据bean。
结合Databinding，让RecyclerView的使用变成ViewModel的定义，基本只需要写xml便可。
```
#### 一. 功能介绍
* 支持多种格式，每种样式一个ViewModel，安顺序组装，每种ViewModel对应一个布局，使用者无需关注各种类型的处理
* 每个ViewModel都可以拥有自己的数据类型，相互之间可以无任何关联
* 支持单条Item中的局部更新 - notifyItemChanged(int position, Object payload)
* 支持ViewModel定义自己的ItemDecoration
* 支持每一个可见的Item的onResume和onPause事件的处理 - onVisibleItemResume()和onVisibleItemPause()
* 对于使用GridLayoutManager和StaggeredGridLayoutManager的，ViewModel中也可通过getSpanCount()返回对应的列数控制，轻松实现单列和多列并存
* 支持过滤列表中的脏数据，具体在ViewModel中isDirtyData(int position, T t)中判断返回
* 支持每个ViewModel自己获取数据，留有request()接口，非常实用多接口的复杂页面
* ViewModel高度解耦，可视为碎片重复使用
* 同一个列表中的多个ViewModel如有业务往来，建议参考MVP模型，ViewModel不要相互持有
* 支持空数据、异常页面、分页加载的模块使用ViewModel方便实现，具体demo中可见
* ViewModel中留有每个Item的点击和长按事件回调处理，可控制开关
* 无需定义Adapter，直接使用CommonRecyclerViewAdapter便可

#### 二. 使用
1. 添加依赖和配置
``` gradle
// 在project的build.gradle中添加
repositories {
    ...
    maven { url 'https://jitpack.io' }
}

dependencies {
    // 替换成最新版本
    implementation 'com.github.xjz-111:recycler-view-model:*.*.*'
    ...
}
```
2. 具体使用
>更多功能见demo介绍
```java
// 这样便实现一个列表的ViewModel
public class ListViewModel extends BaseCommonViewModel<String, ListVmBinding> {

    @Override
    public int getVariable(String s, int position) {
        return com.leslie.recylerviewmodel.BR.lvm;
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.list_vm;
    }
}



/*** ItemDecoration ***/

// 使用isCurrentViewModel(view)来过滤当前的ViewModel来实现只给当前类型添加分割线等处理
@Override
protected RecyclerView.ItemDecoration getItemDecoration() {
return new RecyclerView.ItemDecoration() {
    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
          int count = parent.getLayoutManager().getChildCount();
          for (int i = 0; i < count + 1; i++){
              final View child = parent.getChildAt(i);
              if (null != child && isCurrentViewModel(child)) {
                  // ...
              }
          }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        // isCurrentViewModel()过滤其他的ViewModel类型
        if (isCurrentViewModel(view)) {
            outRect.set(0, 0, 0, 10);
        }
    }
};



/*** ViewModel组装 ***/

List<BaseCommonViewModel> vms
vms.add(new FooterViewModel("### 这是Header ###"));
vms.add(new RefreshViewModel());
vms.add(new RefreshFooterVm(this));
adapter.setViewModels(vms);   



/*** BaseCommonViewModel<T, K extends ViewDatabinding>中部分介绍 ***/

// 当前ViewModel几列显示
public int getSpanCount(){
    return DEFAULT_SPAN_COUNT;
}

// 某条数据是否脏数据，脏数据会被过滤掉不显示
protected boolean isDirtyData(int position, T t){
  return false;
}

// ViewModel实现自加载数据
protected void request(){}
```

#### 二. [demo介绍](https://github.com/xjz-111/recycler-view-model/tree/master/app)
* 简单列表
* 带Header和Footer的列表
* 瀑布流
* Grid
* 带悬停的列表 - 用ItemDecoration实现
* 带悬停的分类列表 - 用ItemDecoration实现
* 刷新 + 分页的列表
* 异常、空数据、正常数据列表切换页面
* ViewModel自加载数据列表
* 视频列表
* 自定义轮播图作为Header的列表 - 轮播图用ViewPager实现，也采用ViewModel的思维，实现解耦，数据分离等，详情请查看[[** carousel **]](https://github.com/xjz-111/carousel)
* 各个ViewModel模块有业务往来的解耦处理列表








