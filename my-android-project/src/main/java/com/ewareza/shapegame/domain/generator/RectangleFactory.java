package com.ewareza.shapegame.domain.generator;

import android.graphics.Point;
import android.graphics.Rect;
import com.ewareza.shapegame.app.shapeColorGame.singleGame.SingleShapeGame;
import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.domain.shape.AbstractShape;
import com.ewareza.shapegame.domain.shape.Rectangle;
import com.ewareza.shapegame.resources.DimenRes;

public class RectangleFactory extends ShapeFactory {
    private static final RectangleFactory INSTANCE = new RectangleFactory(Rectangle.class);

    public RectangleFactory(Class<? extends AbstractShape> shapeClass) {
        super(shapeClass);
    }

    public static RectangleFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public AbstractShape getRandomShape(Rect areaToGenerateShape) {
        Point point = getRandomPointOnCanvas(areaToGenerateShape);
        int left = point.x;
        int top = point.y;
        return getShape(left, top);
    }

    @Override
    public AbstractShape getRandomShapeInNextRow(Rect areaToGenerateShape, int shapeIndex) {
        Point point = getRandomPointOnCanvas(areaToGenerateShape);
        int left = point.x;
        int top = DimenRes.getGameTitleHeight() + (GameUtils.getPaddingBetweenShapes() * (shapeIndex + 1)) + shapeIndex * getMaxRectSize();
        return getShape(left, top);
    }

    private AbstractShape getShape(int left, int top) {
        int randomRectSizeWidth = (int) (getRandomRectSize() * 1.5);
        int randomRectSizeHeight = getMinRectSize();

        int right = left + randomRectSizeWidth;
        int bottom = top + randomRectSizeHeight;

        return new Rectangle(new Rect(left, top, right, bottom), ColorFactory.generateColor());
    }

    @Override
    public AbstractShape getGameTitleShape() {
        int gameTitleHeight = DimenRes.getGameTitleHeight();
        int screenWidth = DimenRes.getScreenWidth();

        int left = screenWidth / 2 - gameTitleHeight;
        int right = screenWidth / 2 + gameTitleHeight;
        Rect rect = new Rect(left, 0, right, gameTitleHeight);

        return new Rectangle(GameUtils.getRectWithPadding(rect, SingleShapeGame.TITLE_SHAPE_PADDING), GameUtils.getGameTitleShapeColor());
    }

    @Override
    public AbstractShape getLearningShape() {
        return new Rectangle(new Rect(GameUtils.LEARNING_SHAPE_LEFT, GameUtils.LEARNING_SHAPE_TOP,
                GameUtils.LEARNING_SHAPE_LEFT + 70, GameUtils.LEARNING_SHAPE_TOP), GameUtils.getLearningShapeColor());
    }
}
