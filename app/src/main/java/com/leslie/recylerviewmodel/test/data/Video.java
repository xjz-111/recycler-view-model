package com.leslie.recylerviewmodel.test.data;

/**
 * 作者：xjzhao
 * 时间：2021-07-16 21:39
 */
public class Video {
    String url;
    String title;
    int position;

    public Video(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
