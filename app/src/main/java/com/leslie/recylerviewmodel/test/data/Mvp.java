package com.leslie.recylerviewmodel.test.data;

/**
 * 作者：xjzhao
 * 时间：2021-07-20 14:15
 */
public class Mvp extends ImgData {
    private boolean isClick = true;
    public Mvp(String url, int width, int height, String title) {
        super(url, width, height, title);
    }

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }
}
