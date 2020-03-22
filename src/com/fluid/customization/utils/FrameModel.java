package com.fluid.customization.utils;

import androidx.fragment.app.Fragment;

public class FrameModel {
    private int background;
    private String title;
    private Fragment fragment;

    public FrameModel(int background, String title, Fragment fragment) {
        this.background = background;
        this.title = title;
        this.fragment = fragment;
    }

    public int getBackground() {
        return background;
    }


    public String getTitle() {
        return title;
    }

    public Fragment getFragment() {
        return fragment;
    }
}
