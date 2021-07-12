package com.leslie.recylerviewmodel.test.hover;

import androidx.recyclerview.widget.RecyclerView;

import com.leslie.recylerviewmodel.test.data.ImgData;
import com.leslie.recylerviewmodel.test.grid.GridViewModel;

import java.util.List;

/**
 * 顶部带悬停
 *
 * 作者：xjzhao
 * 时间：2021-07-09 18:43
 */
public class HoverViewModel extends GridViewModel {

    public HoverViewModel(List<ImgData> list) {
        super(list);
    }

    @Override
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return new HoverItemDecoration(this);
    }


}
