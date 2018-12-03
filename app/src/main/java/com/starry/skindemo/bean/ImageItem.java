package com.starry.skindemo.bean;

/**
 * Created by wangsen on 2018/12/3.
 */

public class ImageItem {

    public String Title;
    public String subTitle;
    private int image;

    public ImageItem() {
    }

    public ImageItem(String title, String subTitle, int image) {
        Title = title;
        this.subTitle = subTitle;
        this.image = image;
    }
}
