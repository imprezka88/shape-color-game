package com.ewareza.shapegame.domain.generator;

import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.resources.DimenRes;
import com.ewareza.shapegame.domain.objects.Circle;
import com.ewareza.shapegame.domain.objects.Shape;
import com.ewareza.shapegame.domain.objects.Square;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CircleGenerator extends ShapeGenerator {
    private static final CircleGenerator INSTANCE = new CircleGenerator(Circle.class);
    private static final List<Integer> radiusSizes = new ArrayList<Integer>();

    public CircleGenerator(Class<? extends Shape> shapeClass) {
        super(shapeClass);
    }

    public static CircleGenerator getInstance() {
        return INSTANCE;
    }

    @Override
    public Shape generateRandomShape() {
/*        int screenWidth = DimenRes.getInstance().getScreenWidth();
        int screenHeight = DimenRes.getInstance().getScreenHeight();
        int gameTitleHeight = DimenRes.getInstance().getGameTitleHeight();

        int centerX = GameUtils.getRandomInt(getMaxRadiusSize(), screenWidth - getMaxRadiusSize());
        int centerY = GameUtils.getRandomInt(getMaxRadiusSize() + gameTitleHeight, screenHeight - getMaxRadiusSize());
        int radius = radiusSizes.get(random.nextInt(radiusSizes.size()));*/

        Square rect = (Square) SquareGenerator.getInstance().generateRandomShape();

        Circle circle = new Circle((rect.getLeft() + rect.getRight()) / 2, (rect.getBottom() + rect.getTop()) / 2, (rect.getRight() - rect.getLeft()) / 2, ColorGenerator.generateColor());

        return circle;
    }

    @Override
    public Shape generateGameTitleShape() {
        int gameTitleHeight = DimenRes.getGameTitleHeight();
        int screenWidth = DimenRes.getScreenWidth();

        return new Circle(screenWidth / 2, gameTitleHeight / 2, gameTitleHeight / 2, GameUtils.getDefaultShapeColor());
    }

    private int getMaxRadiusSize() {
        return Collections.max(radiusSizes);
    }
}
