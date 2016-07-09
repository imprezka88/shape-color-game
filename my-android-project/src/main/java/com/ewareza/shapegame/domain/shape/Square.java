package com.ewareza.shapegame.domain.shape;

import android.graphics.Rect;

public class Square extends Rectangle {
    private static final String SQUARE = "square";

    public Square(Rect rect, int color) {
        super(rect, color);
    }

    @Override
    public String getName() {
        return SQUARE;
    }
}
