package com.leslie.recylerviewmodel.test.alphabetlist;

import android.graphics.Canvas;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leslie.recycler_view_model.BaseCommonViewModel;
import com.leslie.recylerviewmodel.BR;
import com.leslie.recylerviewmodel.R;
import com.leslie.recylerviewmodel.databinding.AlphabetVmBinding;
import com.leslie.recylerviewmodel.databinding.HoverViewBinding;
import com.leslie.recylerviewmodel.test.data.Alphabet;

import java.util.List;

/**
 * 作者：xjzhao
 * 时间：2021-07-12 16:42
 */
public class AlphabetViewModel extends BaseCommonViewModel<Alphabet, AlphabetVmBinding> {
    private Alphabet hover;
    private OnScrollListener onScrollListener;

    public AlphabetViewModel(List<Alphabet> list, OnScrollListener onScrollListener) {
        super(list);
        this.onScrollListener = onScrollListener;
    }

    @Override
    public int getVariable(Alphabet alphabet, int position) {
        return BR.alphabet;
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.alphabet_vm;
    }


    @Override
    protected void initView(@NonNull AlphabetVmBinding binding, @NonNull Alphabet alphabet, int position, List<Object> payloads) {
        super.initView(binding, alphabet, position, payloads);
        boolean isDisplay = isDisplay(alphabet);
        HoverViewBinding hBinding = binding.parentV;
        hBinding.parent.setVisibility(isDisplay ? View.VISIBLE : View.GONE);
        if (isDisplay) hBinding.parentText.setText(alphabet.getParent());
    }

    private boolean isDisplay(Alphabet alphabet){
        return alphabet.getIndex() == 0;
    }


//    private boolean isDisplay(int position){
//        if (position > 0){
//            Alphabet current = getItem(position);
//            Alphabet last = getItem(position - 1);
//            return null != current.getParent() && !current.getParent().equals(last.getParent());
//        }
//        return true;
//    }


    @Override
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return new RecyclerView.ItemDecoration() {
            @Override
            public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
                if (null == hover) hover = getItem(0);
                int childCount = parent.getLayoutManager().getChildCount();
                Object tag;
                int top = 0;
                View child;
                for (int i = 0; i < childCount; i++) {
                    if (null != (child = parent.getChildAt(i)) && null != (tag = child.getTag()) && 0 == ((Alphabet)tag).getIndex() && child.getTop() >= 0){
                        top = child.getTop();
                        if (i > 0) {
                            hover = (Alphabet) parent.getChildAt(i - 1).getTag();
                        }
                        break;
                    }
                }
                onScrollListener.onScroll(top, hover);
            }
        };
    }


    interface OnScrollListener{
        /**
         *
         * @param top      下一个即将悬停的条目距离顶部的距离
         * @param alphabet 悬停条目应该显示的内容用到的bean
         */
        void onScroll(int top, @NonNull Alphabet alphabet);
    }
}
