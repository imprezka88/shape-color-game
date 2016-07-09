package com.ewareza.shapegame.domain.shape;

import android.graphics.Rect;
import com.ewareza.shapegame.app.Game;

public class Rectangle extends AbstractShape {
    private static final String RECTANGLE = "rectangle";

    public Rectangle(Rect rect, int color) {
        super(rect, color, Game.getDrawer(), Game.getMover());
    }

    @Override
    public String getName() {
        return RECTANGLE;
    }

    public Rect asRect() {
        return associatedRect;
    }
}
