package com.leslie.recylerviewmodel.test.data;

/**
 * 作者：xjzhao
 * 时间：2021-07-12 16:42
 */
public class Alphabet {
    private String parent;
    private String title;
    private int index;

    public Alphabet(int index, String parent, String title) {
        this.index = index;
        this.parent = parent;
        this.title = title;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
