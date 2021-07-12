package com.leslie.recylerviewmodel.test.data;

/**
 * 作者：xjzhao
 * 时间：2021-07-09 11:25
 */
public class ItemData {
    String imgUrl;
    String title;

    public ItemData(String imgUrl, String title) {
        this.imgUrl = imgUrl;
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
