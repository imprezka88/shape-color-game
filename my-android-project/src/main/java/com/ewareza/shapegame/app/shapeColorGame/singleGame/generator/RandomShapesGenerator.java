package com.ewareza.shapegame.app.shapeColorGame.singleGame.generator;

import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.shape.generator.*;
import com.ewareza.shapegame.shape.objects.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public enum RandomShapesGenerator {
    INSTANCE;

    private static final Logger logger = Logger.getLogger(RandomShapesGenerator.class.getName());
    private static final int MAX_NUMBER_OF_TRIES_TO_GENERATE_SHAPE = 10;
    private static final int MIN_NUMBER_OF_SHAPES = 5;
    private static final int MAX_NUMBER_OF_SHAPES = 7;
    private static final List<ShapeGenerator> shapeGenerators = new ArrayList<>();
    private final Random random = new Random();
    private ShapeGenerator currentLookedForShapeGenerator;

    static {
        //@TODO retrieve generators by annotation?
        shapeGenerators.add(RectangleGenerator.getInstance());
        shapeGenerators.add(OvalGenerator.getInstance());
        shapeGenerators.add(CircleGenerator.getInstance());
        shapeGenerators.add(TriangleGenerator.getInstance());
        shapeGenerators.add(HeartGenerator.getInstance());
        shapeGenerators.add(SquareGenerator.getInstance());
    }

    public static RandomShapesGenerator getInstance() {
        return INSTANCE;
    }

    public static List<ShapeGenerator> getShapeGenerators() {
        return shapeGenerators;
    }

    public List<Shape> generateShapes() {
        currentLookedForShapeGenerator = null;
        List<Shape> shapes = new ArrayList<>();
        int numberOfShapes = generateRandomNumberOfShapes();

        for (int i = 0; i < numberOfShapes; i++) {
            generateShapeIfPossible(shapes);
        }

        return shapes;
    }

    private ShapeGenerator getRandomShapeGenerator() {
        return shapeGenerators.get(random.nextInt(shapeGenerators.size()));
    }

    private void generateShapeIfPossible(List<Shape> shapes) {
        Shape shape = generateShape();
        int numberOfTriesToGenerateShape = 1;

        while (shouldGenerateDifferentShape(shape, numberOfTriesToGenerateShape, shapes)) {
            shape = generateShape();
            numberOfTriesToGenerateShape++;
        }

        if (canAddNewShape(shape, numberOfTriesToGenerateShape, shapes)) {
            shapes.add(shape);
        }
    }

    private boolean canAddNewShape(Shape shape, int numberOfTriesToGenerateShape, List<Shape> shapes) {
        return !collidesWithExistingShapes(shape, shapes) && numberOfTriesToGenerateShape <= MAX_NUMBER_OF_TRIES_TO_GENERATE_SHAPE;
    }

    private boolean shouldGenerateDifferentShape(Shape shape, int numberOfTriesToGenerateShape, List<Shape> shapes) {
        return collidesWithExistingShapes(shape, shapes) && numberOfTriesToGenerateShape < MAX_NUMBER_OF_TRIES_TO_GENERATE_SHAPE;
    }

    private boolean collidesWithExistingShapes(Shape shapeToExamine, List<Shape> shapes) {
        for (Shape shape : shapes) {
            if (shape.intersects(shapeToExamine)) {
                return true;
            }
        }

        return false;
    }

    public ShapeGenerator getCurrentLookedForShapeGenerator() {
        return currentLookedForShapeGenerator;
    }

    private Shape generateShape() {
        //@TODO get classes implementing Shape through reflection
        ShapeGenerator shapeGenerator = getRandomShapeGenerator();
        if (currentLookedForShapeGenerator == null)
            currentLookedForShapeGenerator = shapeGenerator;


        return shapeGenerator.generateRandomShape();
    }

    private int generateRandomNumberOfShapes() {
        return GameUtils.getRandomInt(MIN_NUMBER_OF_SHAPES, MAX_NUMBER_OF_SHAPES);
    }

    public static List<Shape> generateLearningShapes() {
        List<Shape> shapes = new ArrayList<>();
        for (ShapeGenerator shapeGenerator : shapeGenerators) {
            shapes.add(shapeGenerator.generateRandomShape());
        }

        return shapes;
    }
}
