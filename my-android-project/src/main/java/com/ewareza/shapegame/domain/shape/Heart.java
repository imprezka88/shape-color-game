package com.ewareza.shapegame.domain.shape;

import android.graphics.Point;
import android.graphics.Rect;
import com.ewareza.android.R;

public class Heart extends Shape {
    private static final String HEART = "heart";

    public Heart(Rect rect, int color) {
        super(rect, color);
    }

    @Override
    public boolean contains(Point point) {
        return associatedRect.contains(point.x, point.y);
    }

    @Override
    protected void updateShapesCoordinates() {
    }

    @Override
    public int getGameTitleSoundId() {
        return R.raw.find_heart;
    }

    @Override
    public String getName() {
        return HEART;
    }
}