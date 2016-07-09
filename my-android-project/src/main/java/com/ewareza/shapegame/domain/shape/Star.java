package com.ewareza.shapegame.domain.shape;

import android.graphics.Rect;
import com.ewareza.shapegame.app.Game;

public class Star extends AbstractShape {
    public static final String STAR = "star";

    public Star(Rect associatedRect, int color) {
        super(associatedRect, color, Game.getDrawer(), Game.getMover());
    }

    @Override
    public String getName() {
        return STAR;
    }
}
