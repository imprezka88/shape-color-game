package com.ewareza.shapegame.domain.generator;

import android.graphics.Color;
import android.graphics.Point;
import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.resources.DimenRes;
import com.ewareza.shapegame.domain.objects.Rectangle;
import com.ewareza.shapegame.domain.objects.Shape;

public class RectangleGenerator extends ShapeGenerator {
    private static final RectangleGenerator INSTANCE = new RectangleGenerator(Rectangle.class);

    public RectangleGenerator(Class<? extends Shape> shapeClass) {
        super(shapeClass);
    }

    public static RectangleGenerator getInstance() {
        return INSTANCE;
    }

    @Override
    public Shape generateRandomShape() {
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
    public Shape generateGameTitleShape() {
        int gameTitleHeight = DimenRes.getGameTitleHeight();
        int screenWidth = DimenRes.getScreenWidth();

        int left = screenWidth / 2 - gameTitleHeight;
        int right = screenWidth / 2 + gameTitleHeight;
        Rectangle rectangle = new Rectangle(left, 0, right, gameTitleHeight, GameUtils.getDefaultShapeColor());

        return rectangle;
    }

    @Override
    public Shape generateLearningShape() {
        return new Rectangle(800, 170, 820, 175, Color.BLUE);
    }
}
