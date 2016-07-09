package com.ewareza.shapegame.domain.shape;

import android.graphics.Rect;
import com.ewareza.shapegame.app.Game;

public class Oval extends AbstractShape {
    private static final String OVAL = "oval";

    public Oval(Rect rect, int color) {
        super(rect, color, Game.getDrawer(), Game.getMover());
    }

    @Override
    public String getName() {
        return OVAL;
    }
}
