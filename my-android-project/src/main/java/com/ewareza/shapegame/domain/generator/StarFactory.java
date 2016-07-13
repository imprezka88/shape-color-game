package com.ewareza.shapegame.domain.generator;

import android.graphics.Rect;
import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.domain.shape.AbstractShape;
import com.ewareza.shapegame.domain.shape.Square;
import com.ewareza.shapegame.domain.shape.Star;

public class StarFactory extends ShapeFactory {
    private static final StarFactory INSTANCE = new StarFactory(Star.class);

    public StarFactory(Class<? extends AbstractShape> shapeClass) {
        super(shapeClass);
    }

    public static StarFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public AbstractShape getRandomShapeInNextRow(Rect areaToGenerateShape, int shapeIndex) {
        Square square = (Square) SquareFactory.getInstance().getRandomShape(areaToGenerateShape);
        return new Star(square.getAssociatedRect(), ColorFactory.generateColor());
    }

    @Override
    public AbstractShape getRandomShape(Rect areaToGenerateShape) {
        Square square = (Square) SquareFactory.getInstance().getRandomShape(areaToGenerateShape);
        return new Star(square.getAssociatedRect(), ColorFactory.generateColor());
    }

    @Override
    public AbstractShape getGameTitleShape() {
        Square square = (Square) SquareFactory.getInstance().getGameTitleShape();
        return new Star(square.getAssociatedRect(), GameUtils.getGameTitleShapeColor());
    }

    @Override
    public AbstractShape getLearningShape() {
        Square square = (Square) SquareFactory.getInstance().getLearningShape();
        return new Star(square.getAssociatedRect(), GameUtils.getLearningShapeColor());
    }
}
