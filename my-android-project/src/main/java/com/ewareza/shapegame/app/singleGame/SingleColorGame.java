package com.ewareza.shapegame.app.singleGame;

import com.ewareza.shapegame.resources.DimenRes;
import com.ewareza.shapegame.shape.objects.Shape;
import com.ewareza.shapegame.shape.objects.Square;

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
        for (Shape shape : getSingleGameState().getShapes()) {
            if (isLookedForColor(shape))
                numberOfLookedForObjects++;

        }

        return numberOfLookedForObjects;
    }

    private boolean isLookedForColor(Shape shape) {
        return shape.getColor() == currentLookedForColor;
    }

    @Override
    public boolean isLookedForObjectClicked(Shape shape) {
        return isLookedForColor(shape);
    }

    @Override
    public Shape getGameTitleShape() {
        Square square = new Square(0, 0, DimenRes.getScreenWidth(), DimenRes.getGameTitleHeight(),
                currentLookedForColor);
        return square;
    }
}
