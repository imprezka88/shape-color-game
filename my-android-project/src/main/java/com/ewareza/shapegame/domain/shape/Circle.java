package com.ewareza.shapegame.domain.shape;

import android.graphics.Point;
import android.graphics.Rect;
import com.ewareza.android.R;

public class Circle extends Shape {
    private final static String CIRCLE = "circle";
    private final int radius;
    private int centerX;
    private int centerY;

    public Circle(int centerX, int centerY, int radius, int color) {
        super(new Rect(centerX - radius, centerY - radius, centerX + radius, centerY + radius), color);
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    private int getCenterY() {
        return associatedRect.top + (associatedRect.bottom - associatedRect.top) / 2;
    }

    private int getCenterX() {
        return associatedRect.left + (associatedRect.right - associatedRect.left) / 2;
    }

    public boolean contains(Point point) {
        return (Math.pow(point.x - centerX, 2) + Math.pow(point.y - centerY, 2)) < Math.pow(radius, 2);
    }

    @Override
    protected void updateShapesCoordinates() {
        centerX = getCenterX();
        centerY = getCenterY();
    }

    @Override
    public int getGameTitleSoundId() {
        return R.raw.find_circle;
    }

    @Override
    public String getName() {
        return CIRCLE;
    }
}
