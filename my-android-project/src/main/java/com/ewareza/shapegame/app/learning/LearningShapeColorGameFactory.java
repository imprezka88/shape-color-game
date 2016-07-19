package com.ewareza.shapegame.app.learning;

import com.ewareza.shapegame.app.utils.GameUtils;

public class LearningShapeColorGameFactory {
    public static LearningGame getGame(String gameType) {
        if (gameType.equals(GameUtils.COLOR))
            return new ColorLearningGame();
        else if (gameType.equals(GameUtils.SHAPE))
            return new ShapesLearningGame();

        throw new IllegalArgumentException(String.format("Not recognized learning game type %s", gameType));
    }
}
