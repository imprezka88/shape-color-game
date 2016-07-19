package com.ewareza.shapegame.app.learning;

import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.resources.ImageResources;

public class ColorLearningGame extends LearningGame {
    @Override
    public int getStartGameImageIdentifier() {
        return ImageResources.getColorGameImageIdentifier();
    }

    @Override
    public String getStartGameName() {
        return GameUtils.COLOR;
    }
}
