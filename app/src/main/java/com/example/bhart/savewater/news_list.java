package com.example.bhart.savewater;

public class news_list {
    private String mHeading;
    private String mDesc;
    private String ImageUrl;

    public news_list(String mHeading, String mDesc, String imageUrl) {
        this.mHeading = mHeading;
        this.mDesc = mDesc;
        ImageUrl = imageUrl;
    }

    public String getmHeading() {
        return mHeading;
    }

    public String getmDesc() {
        return mDesc;
    }

    public String getImageUrl() {
        return ImageUrl;
    }
}
