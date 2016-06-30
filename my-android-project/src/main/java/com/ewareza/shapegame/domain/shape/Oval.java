package com.ewareza.shapegame.domain.shape;

import android.graphics.Point;
import android.graphics.Rect;
import com.ewareza.android.R;

public class Oval extends Shape {
    private static final String OVAL = "oval";

    public Oval(int color, Rect rect) {
        super(rect, color);
    }

    @Override
    public boolean contains(Point point) {
        int centerX = (associatedRect.left + associatedRect.right) / 2;
        int radiusX = associatedRect.right - associatedRect.left;
        int centerY = (associatedRect.top + associatedRect.bottom) / 2;
        int radiusY = associatedRect.bottom - associatedRect.top;

        return Math.pow((point.x - centerX), 2) / Math.pow(radiusX, 2) + Math.pow(point.y - centerY, 2) / Math.pow(radiusY, 2) <= 1;
    }

    @Override
    protected void updateShapesCoordinates() {

    }

    @Override
    public int getGameTitleSoundId() {
        //@TODO record oval sound
        return R.raw.start_game;
    }

    @Override
    public String getName() {
        return OVAL;
    }
}
