package com.leslie.recylerviewmodel.test.refresh;

import com.leslie.recylerviewmodel.test.data.ImgData;
import com.leslie.recylerviewmodel.test.grid.GridViewModel;

import java.util.List;

/**
 * 作者：xjzhao
 * 时间：2021-07-13 10:16
 */
public class RefreshViewModel extends GridViewModel {
    public RefreshViewModel(List<ImgData> list) {
        super(list);
    }

    public RefreshViewModel() {
        super(null);
    }
}
