package com.ewareza.shapegame.shape.generator;

import com.ewareza.shapegame.app.GameUtils;
import com.ewareza.shapegame.shape.objects.Heart;
import com.ewareza.shapegame.shape.objects.Rectangle;
import com.ewareza.shapegame.shape.objects.Shape;

public class HeartGenerator extends ShapeGenerator {
    private static final HeartGenerator INSTANCE = new HeartGenerator(Heart.class);

    public HeartGenerator(Class<? extends Shape> shapeClass) {
        super(shapeClass);
    }

    public static HeartGenerator getInstance() {
        return INSTANCE;
    }

    @Override
    public Shape generate() {
        Rectangle rectangle = (Rectangle) RectangleGenerator.getInstance().generate();
        Heart heart = new Heart(rectangle.asRect(), ColorGenerator.generateColor());

        return heart;
    }

    @Override
    public Shape getTitleShape() {
        Rectangle rectangle = (Rectangle) RectangleGenerator.getInstance().getTitleShape();
        return new Heart(rectangle.asRect(), GameUtils.getDefaultShapeColor());
    }
}
