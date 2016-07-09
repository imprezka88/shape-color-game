package com.ewareza.shapegame.app.shapeColorGame.singleGame;

import com.ewareza.shapegame.app.Game;
import com.ewareza.shapegame.domain.Splash;
import com.ewareza.shapegame.domain.generator.SquareFactory;
import com.ewareza.shapegame.domain.shape.AbstractShape;
import com.ewareza.shapegame.domain.shape.Shape;
import com.ewareza.shapegame.domain.shape.Square;

public class SingleColorGame extends SingleGame {
    private final int currentLookedForColor;

    public SingleColorGame(SingleGameState singleGameState, int color) {
        super(singleGameState);
        currentLookedForColor = color;
        singleGameState.setNumberOfLookedForObjects(countNumberOfLookedForObjects());
        setNumberOfLookedForShapesOnScreen(singleGameState.getNumberOfLookedForObjects());
    }

    public int getCurrentLookedForColor() {
        return currentLookedForColor;
    }

    private int countNumberOfLookedForObjects() {
        int numberOfLookedForObjects = 0;
        for (AbstractShape shape : getSingleGameState().getShapes()) {
            if (isLookedForColor(shape))
                numberOfLookedForObjects++;

        }

        return numberOfLookedForObjects;
    }

    private boolean isLookedForColor(AbstractShape shape) {
        return shape.getColor() == currentLookedForColor;
    }

    @Override
    public boolean isLookedForObjectClicked(AbstractShape shape) {
        return isLookedForColor(shape);
    }

    @Override
    public Shape getGameTitleShape() {
        Square square = (Square) SquareFactory.getInstance().getGameTitleShape();

        return new Splash(square.getAssociatedRect(), currentLookedForColor, Game.getDrawer());
    }
}
