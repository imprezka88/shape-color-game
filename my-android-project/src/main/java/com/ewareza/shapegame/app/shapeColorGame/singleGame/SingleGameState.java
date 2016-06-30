package com.ewareza.shapegame.app.shapeColorGame.singleGame;

import com.ewareza.shapegame.domain.shape.Shape;

import java.util.List;

public class SingleGameState {
    private final List<Shape> shapes;
    private final Shape lookedForObject;
    private int numberOfLookedForObjects;

    public SingleGameState(List<Shape> shapes) {
        this.shapes = shapes;
        lookedForObject = getFirstGeneratedShape(shapes);
    }

    private Shape getFirstGeneratedShape(List<Shape> shapes) {
        return shapes.get(0);
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public int getNumberOfLookedForObjects() {
        return numberOfLookedForObjects;
    }

    public void setNumberOfLookedForObjects(int numberOfLookedForObjects) {
        this.numberOfLookedForObjects = numberOfLookedForObjects;
    }

    public Shape getLookedForObject() {
        return lookedForObject;
    }
}
