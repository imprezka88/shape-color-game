package com.ewareza.shapegame.domain.generator;

import android.graphics.Rect;
import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.domain.shape.AbstractShape;
import com.ewareza.shapegame.domain.shape.Oval;
import com.ewareza.shapegame.domain.shape.Rectangle;

public class OvalFactory extends ShapeFactory {
    private static final OvalFactory INSTANCE = new OvalFactory(Oval.class);

    public OvalFactory(Class<? extends AbstractShape> shapeClass) {
        super(shapeClass);
    }

    public static ShapeFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public AbstractShape getRandomShapeInNextRow(Rect areaToGenerateShape, int shapeIndex) {
        Rectangle rectangle = (Rectangle) RectangleFactory.getInstance().getRandomShapeInNextRow(areaToGenerateShape, shapeIndex);
        return new Oval(rectangle.asRect(), ColorFactory.generateColor());
    }

    @Override
    public AbstractShape getRandomShape(Rect areaToGenerateShape) {
        Rectangle rectangle = (Rectangle) RectangleFactory.getInstance().getRandomShape(areaToGenerateShape);
        return new Oval(rectangle.asRect(), ColorFactory.generateColor());
    }

    @Override
    public AbstractShape getGameTitleShape() {
        Rectangle rectangle = (Rectangle) RectangleFactory.getInstance().getGameTitleShape();
        Rect rect = rectangle.asRect();

        return new Oval(rect, GameUtils.getGameTitleShapeColor());
    }

    @Override
    public AbstractShape getLearningShape() {
        Rect rect = RectangleFactory.getInstance().getLearningShape().getAssociatedRect();
        return new Oval(rect, GameUtils.getLearningShapeColor());
    }
}
