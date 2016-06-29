package com.ewareza.shapegame.app.singleGame;

import android.graphics.Canvas;
import android.graphics.Point;
import com.ewareza.shapegame.app.GameUtils;
import com.ewareza.shapegame.resources.SoundResources;
import com.ewareza.shapegame.shape.objects.Shape;

import java.util.ArrayList;
import java.util.List;

/*
* Represents one single level of game i.e. one set one generated shapes and generated looked for shape/color
* */
public abstract class SingleGame {
    private SingleGameState singleGameState;

    private int numberOfLookedForShapesOnScreen;

    public SingleGame(SingleGameState singleGameState) {
        this.singleGameState = singleGameState;
    }

    public SingleGameState getSingleGameState() {
        return singleGameState;
    }

    public Shape getCurrentLookedForObject() {
        return singleGameState.getLookedForObject();
    }

    public abstract boolean isLookedForObjectClicked(Shape shape);

    public List<Shape> getShapes() {
        return singleGameState.getShapes();
    }

    public abstract Shape getGameTitleShape();

    public void onScreenTouched(Point point) {
        List<Shape> toRemove = new ArrayList<>();
        for (Shape shape : getShapes()) {
            if (shape.contains(point) && isLookedForObjectClicked(shape)) {
                SoundResources.getInstance().playCorrectShapeClickedSound();
                toRemove.add(shape);
            }
        }

        if (toRemove.isEmpty())
            SoundResources.getInstance().playWrongShapeClickedSound();
        else
            removeTouchedShapes(toRemove);
    }

    private void removeTouchedShapes(List<Shape> toRemove) {
        for (Shape shape : toRemove) {
            getShapes().remove(shape);
            numberOfLookedForShapesOnScreen--;
        }
    }

    public int getNumberOfLookedForShapesOnScreen() {
        return numberOfLookedForShapesOnScreen;
    }

    protected void setNumberOfLookedForShapesOnScreen(int numberOfLookedForShapesOnScreen) {
        this.numberOfLookedForShapesOnScreen = numberOfLookedForShapesOnScreen;
    }

    public void update() {
        for (Shape shape : getShapes()) {
            shape.update();
        }
    }

    public void draw(Canvas canvas) {
        for (Shape shape : getShapes()) {
            shape.draw(canvas, GameUtils.getFilledPaint());
        }
    }
}
