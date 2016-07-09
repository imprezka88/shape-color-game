package com.ewareza.shapegame.domain.generator;

import android.graphics.Rect;
import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.domain.shape.AbstractShape;
import com.ewareza.shapegame.domain.shape.Heart;
import com.ewareza.shapegame.domain.shape.Square;

public class HeartFactory extends ShapeFactory {
    private static final HeartFactory INSTANCE = new HeartFactory(Heart.class);

    public HeartFactory(Class<? extends AbstractShape> shapeClass) {
        super(shapeClass);
    }

    public static HeartFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public AbstractShape getRandomShape(Rect areaToGenerateShape) {
        Square square = (Square) SquareFactory.getInstance().getRandomShape(areaToGenerateShape);
        return new Heart(square.asRect(), ColorFactory.generateColor());
    }

    @Override
    public AbstractShape getRandomShapeInNextRow(Rect areaToGenerateShape, int shapeIndex) {
        Square square = (Square) SquareFactory.getInstance().getRandomShapeInNextRow(areaToGenerateShape, shapeIndex);
        return new Heart(square.asRect(), ColorFactory.generateColor());
    }

    @Override
    public AbstractShape getGameTitleShape() {
        Square square = (Square) SquareFactory.getInstance().getGameTitleShape();
        return new Heart(square.asRect(), GameUtils.getGameTitleShapeColor());
    }

    @Override
    public AbstractShape getLearningShape() {
        int left = GameUtils.LEARNING_SHAPE_LEFT;
        int top = GameUtils.LEARNING_SHAPE_TOP;
        return new Heart(new Rect(left, top, left + 5, top + 10), GameUtils.getLearningShapeColor());
    }
}
