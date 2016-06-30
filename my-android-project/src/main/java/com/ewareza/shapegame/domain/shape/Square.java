package com.ewareza.shapegame.domain.shape;

import android.graphics.Rect;
import com.ewareza.android.R;

public class Square extends Rectangle {
    private static final String SQUARE = "square";

    public Square(Rect rect, int color) {
        super(rect, color);
    }

    @Override
    public int getGameTitleSoundId() {
        return R.raw.find_square;
    }

    @Override
    public String getName() {
        return SQUARE;
    }
}
