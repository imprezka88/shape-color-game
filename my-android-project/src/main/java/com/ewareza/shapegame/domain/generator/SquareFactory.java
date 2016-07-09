package com.ewareza.shapegame.domain.generator;

import android.graphics.Point;
import android.graphics.Rect;
import com.ewareza.shapegame.app.shapeColorGame.singleGame.SingleShapeGame;
import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.domain.shape.AbstractShape;
import com.ewareza.shapegame.domain.shape.Square;
import com.ewareza.shapegame.resources.DimenRes;

public class SquareFactory extends ShapeFactory {
    private static final SquareFactory INSTANCE = new SquareFactory(Square.class);

    public SquareFactory(Class<? extends AbstractShape> shapeClass) {
        super(shapeClass);
    }

    public static SquareFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public AbstractShape getRandomShapeInNextRow(Rect areaToGenerateShape, int shapeIndex) {
        Point point = getRandomPointOnCanvas(areaToGenerateShape);
        int left = point.x;
        int top = DimenRes.getGameTitleHeight() + (GameUtils.getPaddingBetweenShapes() * (shapeIndex + 1)) + shapeIndex * getMaxRectSize();
        int randomRectSize = getRandomRectSize();
        int right = left + randomRectSize;
        int bottom = top + randomRectSize;

        return new Square(new Rect(left, top, right, bottom), ColorFactory.generateColor());
    }

    @Override
    public AbstractShape getRandomShape(Rect areaToGenerateShape) {
        Point point = getRandomPointOnCanvas(areaToGenerateShape);
        int left = point.x;
        int top = point.y;
        int randomRectSize = getRandomRectSize();
        int right = left + randomRectSize;
        int bottom = top + randomRectSize;

        return new Square(new Rect(left, top, right, bottom), ColorFactory.generateColor());
    }

    @Override
    public AbstractShape getGameTitleShape() {
        int gameTitleHeight = DimenRes.getGameTitleHeight();
        int screenWidth = DimenRes.getScreenWidth();

        int left = screenWidth / 2 - gameTitleHeight / 2;
        int right = screenWidth / 2 + gameTitleHeight / 2;
        Rect rect = new Rect(left, 0, right, gameTitleHeight);

        return new Square(GameUtils.getRectWithPadding(rect, SingleShapeGame.TITLE_SHAPE_PADDING), GameUtils.getGameTitleShapeColor());
    }

    @Override
    public AbstractShape getLearningShape() {
        int top = GameUtils.LEARNING_SHAPE_TOP;
        int left = GameUtils.LEARNING_SHAPE_LEFT;
        int size = 5;
        return new Square(new Rect(left, top, left + size, top + size), GameUtils.getLearningShapeColor());
    }
}
