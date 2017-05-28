package com.myexample.imageviewer;

public class Image {
    private final String title;
    private final String url;

    public Image(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String title() {
        return title;
    }

    public String url() {
        return url;
    }
}
