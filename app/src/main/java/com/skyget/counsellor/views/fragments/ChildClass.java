package com.skyget.counsellor.views.fragments;

import android.widget.ImageView;

public class ChildClass {

    private ImageView thumbnail2;

    // Constructor of the class
    // to initialize the variable*
    public ChildClass(ImageView thumbnail2)
    {
        this.thumbnail2 = thumbnail2;
    }

    public ImageView getThumbnail2() {
        return thumbnail2;
    }

    public void setThumbnail2(ImageView thumbnail2) {
        this.thumbnail2 = thumbnail2;
    }
}
