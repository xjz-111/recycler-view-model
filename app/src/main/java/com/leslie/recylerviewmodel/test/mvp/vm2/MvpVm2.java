package com.leslie.recylerviewmodel.test.mvp.vm2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leslie.recylerviewmodel.BR;
import com.leslie.recylerviewmodel.R;
import com.leslie.recylerviewmodel.databinding.MvpVm2Binding;
import com.leslie.recylerviewmodel.test.data.Mvp;
import com.leslie.recylerviewmodel.test.mvp.base.OnListener;
import com.leslie.recylerviewmodel.test.mvp.base.PresenterBaseViewModel;

import java.util.List;

/**
 * 作者：xjzhao
 * 时间：2021-07-20 13:56
 */
public class MvpVm2 extends PresenterBaseViewModel<Mvp, MvpVm2Binding> {


    public MvpVm2(List<Mvp> list, OnListener<Mvp> onListener) {
        super(list, onListener);
        presenter = new Mvp2Presenter(this);
    }

    @Override
    public int getVariable(Mvp mvp, int position) {
        return BR.mvp2;
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.mvp_vm2;
    }

    @Override
    protected void initView(@NonNull MvpVm2Binding binding, @NonNull Mvp mvp, int position, List<Object> payloads) {
        super.initView(binding, mvp, position, payloads);
        binding.btn.setOnClickListener(v -> {
            mvp.setClick(false);
            notifyItemChanged(position);
            if (null != onListener){
                onListener.click(position, mvp);
            }
        });
    }

    // 一个简单的分割线
    @Override
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.onDraw(c, parent, state);
                Paint paint = new Paint();
                paint.setColor(Color.GRAY);
                int count = parent.getLayoutManager().getChildCount();
                for (int i = 0; i < count + 1; i++){
                    final View child = parent.getChildAt(i);
                    if (null != child && isCurrentViewModel(child)) {
                        float left = child.getLeft();
                        float top = child.getBottom();
                        float right = child.getRight();
                        float bottom = top + 5;

                        c.drawRect(left, top, right, bottom, paint);
                    }
                }
            }

            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                if (isCurrentViewModel(view)) {
                    outRect.set(0, 0, 0, 5);
                }
            }
        };
    }
}
