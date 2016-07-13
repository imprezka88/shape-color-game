package com.ewareza.shapegame.app.shapeColorGame;

import android.graphics.drawable.Drawable;
import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.resources.ImageResources;

public class ShapeGame extends ShapeColorGame {
    public ShapeGame() {
        super(GameUtils.SHAPE);
    }

    @Override
    public String getNextGameName() {
        return GameUtils.COLOR;
    }

    @Override
    public int getNextGameImageIdentifier() {
        return ImageResources.getColorGameImageIdentifier();
    }

    @Override
    public Drawable getNextGameImage() {
        return ImageResources.getColorGameImage();
    }
}
