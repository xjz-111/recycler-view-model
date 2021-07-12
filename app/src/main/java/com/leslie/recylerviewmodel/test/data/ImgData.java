package com.leslie.recylerviewmodel.test.data;

/**
 * 作者：xjzhao
 * 时间：2021-07-09 10:17
 */
public class ImgData {
    String url;
    int width;
    int height;
    String title;

    public ImgData(String url) {
        this.url = url;
    }

    public ImgData(String url, int width, int height, String title) {
        this.url = url;
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
