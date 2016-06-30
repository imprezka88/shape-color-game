package com.ewareza.shapegame.domain.generator;

import android.graphics.Rect;
import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.domain.shape.Heart;
import com.ewareza.shapegame.domain.shape.Rectangle;
import com.ewareza.shapegame.domain.shape.Shape;

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

    @Override
    public Shape generateLearningShape() {
        int left = GameUtils.LEARNING_SHAPE_LEFT;
        int top = GameUtils.LEARNING_SHAPE_TOP;
        return new Heart(new Rect(left, top, left + 5, top + 10), GameUtils.getLearningShapeColor());
    }
}
