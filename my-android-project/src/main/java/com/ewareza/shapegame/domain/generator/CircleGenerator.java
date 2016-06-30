package com.ewareza.shapegame.domain.generator;

import android.graphics.Rect;
import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.resources.DimenRes;
import com.ewareza.shapegame.domain.shape.Circle;
import com.ewareza.shapegame.domain.shape.Shape;
import com.ewareza.shapegame.domain.shape.Square;

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
        Square square = (Square) SquareGenerator.getInstance().generateRandomShape();
        Rect rect = square.getAssociatedRect();
        Circle circle = new Circle((rect.left + rect.right) / 2, (rect.bottom + rect.top) / 2, (rect.right - rect.left) / 2, ColorGenerator.generateColor());

        return circle;
    }

    @Override
    public Shape generateGameTitleShape() {
        int gameTitleHeight = DimenRes.getGameTitleHeight();
        int screenWidth = DimenRes.getScreenWidth();

        return new Circle(screenWidth / 2, gameTitleHeight / 2, gameTitleHeight / 2, GameUtils.getDefaultShapeColor());
    }

    @Override
    public Shape generateLearningShape() {
        return new Circle(GameUtils.LEARNING_SHAPE_LEFT, GameUtils.LEARNING_SHAPE_TOP, 5, GameUtils.getLearningShapeColor());
    }
}
