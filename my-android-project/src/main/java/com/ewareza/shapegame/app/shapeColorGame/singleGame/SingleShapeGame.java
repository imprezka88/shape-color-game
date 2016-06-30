package com.ewareza.shapegame.app.shapeColorGame.singleGame;

import com.ewareza.shapegame.shape.generator.ShapeGenerator;
import com.ewareza.shapegame.shape.objects.Shape;

public class SingleShapeGame extends SingleGame {
    private final ShapeGenerator currentLookedForShapeGenerator;

    public SingleShapeGame(SingleGameState singleGameState, ShapeGenerator lookedForShape) {
        super(singleGameState);
        currentLookedForShapeGenerator = lookedForShape;
        singleGameState.setNumberOfLookedForObjects(countNumberOfLookedForObjects());
        setNumberOfLookedForShapesOnScreen(singleGameState.getNumberOfLookedForObjects());
    }

    private int countNumberOfLookedForObjects() {
        int numberOfLookedForObjects = 0;
        for (Shape shape : getSingleGameState().getShapes()) {
            if (isLookedForShape(shape))
                numberOfLookedForObjects++;
        }

        return numberOfLookedForObjects;
    }

    private boolean isLookedForShape(Shape shape) {
        return shape.getClass().equals(getSingleGameState().getLookedForObject().getClass());
    }

    @Override
    public boolean isLookedForObjectClicked(Shape shape) {
        return isLookedForShape(shape);
    }

    @Override
    public Shape getGameTitleShape() {
        return currentLookedForShapeGenerator.generateGameTitleShape();
    }
}
