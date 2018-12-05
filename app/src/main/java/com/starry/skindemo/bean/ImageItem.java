package com.starry.skindemo.bean;

/**
 * Created by wangsen on 2018/12/3.
 */

public class ImageItem {
    public ImageItem() {
    }

    public ImageItem(String title, String subtitle, int image) {
        this.title = title;
        this.subtitle = subtitle;
        this.image = image;
    }

    public String title;
    public String subtitle;
    public int image;
}
