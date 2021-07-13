package com.leslie.recylerviewmodel.test.refresh;

public class Footer implements IFooter {
    private int state = FOOTER_NORMAL;


    public Footer() {

    }

    public int getState() {
        return state;
    }

    public Footer setState(int state) {
        this.state = state;
        return this;
    }
}
