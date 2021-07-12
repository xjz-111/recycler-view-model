package com.leslie.recylerviewmodel.test.headerfoooter;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.leslie.recycler_view_model.BaseCommonViewModel;
import com.leslie.recylerviewmodel.BR;
import com.leslie.recylerviewmodel.R;
import com.leslie.recylerviewmodel.test.data.ImgData;

import java.util.List;

/**
 * header的ViewModel
 *
 * 作者：xjzhao
 * 时间：2021-07-09 00:57
 */
public class HeaderViewModel extends BaseCommonViewModel<ImgData> {

    public HeaderViewModel(List<ImgData> list) {
        super(list);
    }

    @Override
    public int getVariable(ImgData data, int position) {
        return BR.headerVm;
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.header_vm;
    }

    @Override
    protected void initView(@NonNull ViewDataBinding binding, @NonNull ImgData imgData, int position, List<Object> payloads) {
        super.initView(binding, imgData, position, payloads);
    }

    // 一个简单的分割线
    @Override
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return new RecyclerView.ItemDecoration() {

            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                if (isCurrentViewModel(view)) {
                    outRect.set(0, 0, 0, 10);
                }
            }
        };
    }
}
