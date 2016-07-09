package com.ewareza.shapegame.app.learning;

import android.graphics.Rect;
import com.ewareza.shapegame.app.GameSettings;
import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.domain.generator.ShapeFactory;
import com.ewareza.shapegame.domain.shape.AbstractShape;
import com.ewareza.shapegame.resources.DimenRes;

import java.util.ArrayList;
import java.util.List;

public class LearningShapesGenerator {
    private static Rect areaToGenerate = initAreaToGenerate();

    public static List<AbstractShape> generateShapesForSecondLearningPhase() {
        List<AbstractShape> shapes = new ArrayList<>();
        for (ShapeFactory shapeFactory : GameSettings.getShapeFactories()) {
            AbstractShape shape = shapeFactory.getRandomShape(areaToGenerate);

            while (GameUtils.collidesWithExistingShapes(shape, shapes)) {
                shape = shapeFactory.getRandomShape(areaToGenerate);
            }

            shape.setColor(GameUtils.getLearningShapeColor());
            shapes.add(shape);
        }

        return shapes;
    }

    private static Rect initAreaToGenerate() {
        return new Rect(0, 0, DimenRes.getScreenWidth(),
                DimenRes.getScreenHeight());
    }

    public static List<AbstractShape> generateShapesForFirstLearningPhase() {
        List<AbstractShape> learningShapes = new ArrayList<>();
        for (ShapeFactory shapeFactory : GameSettings.getShapeFactories()) {
            learningShapes.add(shapeFactory.getLearningShape());
        }

        return learningShapes;
    }
}
