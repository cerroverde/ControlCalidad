package com.cupagroup.controlcalidad.utils;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;

public class CustomOutlineProvider extends ViewOutlineProvider {
    int roundCorner;
    public CustomOutlineProvider(int round){
        roundCorner = round;
    }

    @Override
    public void getOutline(View view, Outline outline) {
        outline.setRoundRect(-0,-0, view.getWidth(), view.getHeight(), roundCorner);
    }
}
