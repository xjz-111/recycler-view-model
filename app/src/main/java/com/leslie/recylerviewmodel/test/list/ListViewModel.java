package com.leslie.recylerviewmodel.test.list;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leslie.recycler_view_model.BaseCommonViewModel;
import com.leslie.recylerviewmodel.R;
import com.leslie.recylerviewmodel.databinding.ListVmBinding;

import java.util.List;

/**
 * 简单的列表
 *
 * 作者：xjzhao
 * 时间：2021-07-08 13:59
 */
public class ListViewModel extends BaseCommonViewModel<String, ListVmBinding> {

    public ListViewModel(List<String> list) {
        super(list);
    }

    @Override
    public int getVariable(String s, int position) {
        return com.leslie.recylerviewmodel.BR.lvm;
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.list_vm;
    }



    // 一个简单的分割线
    @Override
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.onDraw(c, parent, state);
                Paint paint = new Paint();
                paint.setColor(Color.BLUE);
                int count = parent.getLayoutManager().getChildCount();
                for (int i = 0; i < count + 1; i++){
                    final View child = parent.getChildAt(i);
                    if (null != child && isCurrentViewModel(child)) {
                        float left = child.getLeft();
                        float top = child.getBottom();
                        float right = child.getRight();
                        float bottom = top + 10;

                        c.drawRect(left, top, right, bottom, paint);
                    }
                }
            }

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
