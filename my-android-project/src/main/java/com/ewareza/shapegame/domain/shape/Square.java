package com.ewareza.shapegame.domain.shape;

import com.ewareza.android.R;

public class Square extends Rectangle {
    private static final String SQUARE = "square";

    public Square(int left, int top, int right, int bottom, int color) {
        super(left, top, right, bottom, color);
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
