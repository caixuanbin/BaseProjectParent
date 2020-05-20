package com.bean;

public class Msg {
    private String title;
    private String context;
    private String extraInfo;

    public Msg(String title, String context, String extraInfo) {
        this.title = title;
        this.context = context;
        this.extraInfo = extraInfo;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }
}
