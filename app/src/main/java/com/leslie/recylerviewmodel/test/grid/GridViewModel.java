package com.leslie.recylerviewmodel.test.grid;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.leslie.recycler_view_model.BaseCommonViewModel;
import com.leslie.recylerviewmodel.R;
import com.leslie.recylerviewmodel.databinding.GridVmBinding;
import com.leslie.recylerviewmodel.test.data.ImgData;

import java.util.List;

/**
 * Grid
 *
 * 作者：xjzhao
 * 时间：2021-07-09 12:41
 */
public class GridViewModel extends BaseCommonViewModel<ImgData, GridVmBinding> {

    public GridViewModel(List<ImgData> list) {
        super(list);
    }

    @Override
    public int getVariable(ImgData imgData, int position) {
        return BR.gridVm;
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.grid_vm;
    }

    @Override
    public int getSpanCount() {
        return 2;
    }

    @Override
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }

            @Override
            public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
            }

            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
            }
        };
    }
}
