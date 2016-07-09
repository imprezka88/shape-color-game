package com.ewareza.shapegame.domain.shape;

import android.graphics.Rect;
import com.ewareza.shapegame.app.Game;

public class Heart extends AbstractShape {
    private static final String HEART = "heart";

    public Heart(Rect rect, int color) {
        super(rect, color, Game.getDrawer(), Game.getMover());
    }

    @Override
    public String getName() {
        return HEART;
    }
}
