package com.edisonjimenez.imageviewer;

import java.io.Serializable;

public class Photo implements Serializable{

    private static final long serialVersionUID = 1L;

    private String mTitle;
    private String mLink;

    public Photo(String mTitle, String mLink) {
        this.mTitle = mTitle;
        this.mLink = mLink;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getLink() {
        return mLink;
    }



    @Override
    public String toString() {
        return "Photo{" +
                "mTitle='" + mTitle + '\'' +
                ", mLink='" + mLink + '\'' +
                '}';
    }
}
