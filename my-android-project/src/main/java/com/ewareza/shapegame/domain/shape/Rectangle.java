package com.ewareza.shapegame.domain.shape;

import android.graphics.Point;
import android.graphics.Rect;
import com.ewareza.android.R;

public class Rectangle extends Shape {
    private static final String RECTANGLE = "rectangle";
    private int left = associatedRect.left;
    private int top;
    private int right;
    private int bottom;

    public Rectangle(Rect rect, int color) {
        super(new Rect(rect.left, rect.top, rect.right, rect.bottom), color);
        this.left = rect.left;
        this.top = rect.top;
        this.right = rect.right;
        this.bottom = rect.bottom;
    }

    public boolean contains(Point point) {
        return associatedRect.contains(point.x, point.y);
    }

    @Override
    protected void updateShapesCoordinates() {
        left = associatedRect.left;
        top = associatedRect.top;
        right = associatedRect.right;
        bottom = associatedRect.bottom;
    }

    @Override
    public int getGameTitleSoundId() {
        return R.raw.find_rectangle;
    }

    @Override
    public String getName() {
        return RECTANGLE;
    }


    public int getLeft() {
        return left;
    }

    public int getTop() {
        return top;
    }

    public int getRight() {
        return right;
    }

    public int getBottom() {
        return bottom;
    }

    public Rect asRect() {
        return new Rect(left, top, right, bottom);
    }
}
