package com.ewareza.shapegame.shape.generator;

import android.graphics.Point;
import com.ewareza.shapegame.app.GameUtils;
import com.ewareza.shapegame.resources.DimenRes;
import com.ewareza.shapegame.shape.objects.Rectangle;
import com.ewareza.shapegame.shape.objects.Shape;

public class RectangleGenerator extends ShapeGenerator {
    private static final RectangleGenerator INSTANCE = new RectangleGenerator(Rectangle.class);

    public RectangleGenerator(Class<? extends Shape> shapeClass) {
        super(shapeClass);
    }

    public static RectangleGenerator getInstance() {
        return INSTANCE;
    }

    @Override
    public Shape generate() {
        Point point = generateRandomPointOnCanvas();
        int left = point.x;
        int top = point.y;
        int randomRectSizeWidth = getRandomRectSize();
        int randomRectSizeHeight = getRandomRectSize();

        while (randomRectSizeHeight == randomRectSizeWidth) {
            randomRectSizeHeight = getRandomRectSize();
        }

        int right = left + randomRectSizeWidth;
        int bottom = top + randomRectSizeHeight;

        return new Rectangle(left, top, right, bottom, ColorGenerator.generateColor());
    }

    @Override
    public Shape getTitleShape() {
        int gameTitleHeight = DimenRes.getGameTitleHeight();
        int screenWidth = DimenRes.getScreenWidth();

        int left = screenWidth / 2 - gameTitleHeight;
        int right = screenWidth / 2 + gameTitleHeight;
        Rectangle rectangle = new Rectangle(left, 0, right, gameTitleHeight, GameUtils.getDefaultShapeColor());

        return rectangle;
    }
}
