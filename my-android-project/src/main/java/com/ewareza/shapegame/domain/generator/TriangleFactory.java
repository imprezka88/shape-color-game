package com.ewareza.shapegame.domain.generator;

import android.graphics.Rect;
import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.domain.shape.AbstractShape;
import com.ewareza.shapegame.domain.shape.Square;
import com.ewareza.shapegame.domain.shape.Triangle;

public class TriangleFactory extends ShapeFactory {
    private static TriangleFactory INSTANCE = new TriangleFactory(Triangle.class);

    public TriangleFactory(Class<? extends AbstractShape> shapeClass) {
        super(shapeClass);
    }

    public static TriangleFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public AbstractShape getRandomShape(Rect areaToGenerateShape) {
        Square square = (Square) SquareFactory.getInstance().getRandomShape(areaToGenerateShape);
        return new Triangle(square.getAssociatedRect(), ColorFactory.generateColor());
    }

    @Override
    public AbstractShape getRandomShapeInNextRow(Rect areaToGenerateShape, int shapeIndex) {
        Square square = (Square) SquareFactory.getInstance().getRandomShapeInNextRow(areaToGenerateShape, shapeIndex);
        return new Triangle(square.getAssociatedRect(), ColorFactory.generateColor());
    }

    @Override
    public AbstractShape getGameTitleShape() {
        Square square = (Square) SquareFactory.getInstance().getGameTitleShape();
        return new Triangle(square.getAssociatedRect(), GameUtils.getGameTitleShapeColor());
    }

    @Override
    public AbstractShape getLearningShape() {
        int left = GameUtils.LEARNING_SHAPE_LEFT;
        int top = GameUtils.LEARNING_SHAPE_TOP;
        return new Triangle(new Rect(left, top, left + 5, top + 10), GameUtils.getLearningShapeColor());
    }
}