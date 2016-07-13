package com.ewareza.shapegame.app;

import com.ewareza.shapegame.domain.generator.*;
import com.ewareza.shapegame.domain.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class GameSettings {
    private static final List<ShapeFactory> shapeFactories = new ArrayList<>();

    static {
        //@TODO retrieve generators by annotation?
        shapeFactories.add(RectangleFactory.getInstance());
        shapeFactories.add(Circle.CircleFactory.getInstance());

        shapeFactories.add(SquareFactory.getInstance());
        shapeFactories.add(TriangleFactory.getInstance());

        shapeFactories.add(OvalFactory.getInstance());
        shapeFactories.add(HeartFactory.getInstance());

        shapeFactories.add(StarFactory.getInstance());
    }

    public static List<ShapeFactory> getShapeFactories() {
        return shapeFactories;
    }
}

