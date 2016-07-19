package com.ewareza.shapegame.app.learning;

import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.resources.ImageResources;

public class ShapesLearningGame extends LearningGame {
    @Override
    public int getStartGameImageIdentifier() {
        return ImageResources.getShapeGameImageIdentifier();
    }

    @Override
    public String getStartGameName() {
        return GameUtils.SHAPE;
    }
}
