package com.ewareza.shapegame.domain.generator;

import android.graphics.Point;
import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.resources.DimenRes;
import com.ewareza.shapegame.domain.objects.Shape;
import com.ewareza.shapegame.domain.objects.Square;

public class SquareGenerator extends ShapeGenerator {
    private static final SquareGenerator INSTANCE = new SquareGenerator(Square.class);

    public SquareGenerator(Class<? extends Shape> shapeClass) {
        super(shapeClass);
    }

    public static SquareGenerator getInstance() {
        return INSTANCE;
    }

    @Override
    public Shape generateRandomShape() {
        Point point = generateRandomPointOnCanvas();
        int left = point.x;
        int top = point.y;
        int randomRectSize = getRandomRectSize();
        int right = left + randomRectSize;
        int bottom = top + randomRectSize;

        return new Square(left, top, right, bottom, ColorGenerator.generateColor());
    }

    @Override
    public Shape generateGameTitleShape() {
        int gameTitleHeight = DimenRes.getGameTitleHeight();
        int screenWidth = DimenRes.getScreenWidth();

        int left = screenWidth / 2 - gameTitleHeight / 2;
        int right = screenWidth / 2 + gameTitleHeight / 2;
        Square square = new Square(left, 0, right, gameTitleHeight, GameUtils.getDefaultShapeColor());

        return square;
    }
}
