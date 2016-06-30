package com.ewareza.shapegame.domain.generator;

import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.domain.objects.Heart;
import com.ewareza.shapegame.domain.objects.Rectangle;
import com.ewareza.shapegame.domain.objects.Shape;

public class HeartGenerator extends ShapeGenerator {
    private static final HeartGenerator INSTANCE = new HeartGenerator(Heart.class);

    public HeartGenerator(Class<? extends Shape> shapeClass) {
        super(shapeClass);
    }

    public static HeartGenerator getInstance() {
        return INSTANCE;
    }

    @Override
    public Shape generateRandomShape() {
        Rectangle rectangle = (Rectangle) RectangleGenerator.getInstance().generateRandomShape();
        Heart heart = new Heart(rectangle.asRect(), ColorGenerator.generateColor());

        return heart;
    }

    @Override
    public Shape generateGameTitleShape() {
        Rectangle rectangle = (Rectangle) RectangleGenerator.getInstance().generateGameTitleShape();
        return new Heart(rectangle.asRect(), GameUtils.getDefaultShapeColor());
    }
}
