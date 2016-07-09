package com.ewareza.shapegame.domain.shape;

import android.graphics.Rect;
import com.ewareza.shapegame.app.Game;

public class Triangle extends AbstractShape {
    private static final String TRIANGLE = "triangle";

    public Triangle(Rect rect, int color) {
        super(rect, color, Game.getDrawer(), Game.getMover());
    }

    @Override
    public String getName() {
        return TRIANGLE;
    }
}
