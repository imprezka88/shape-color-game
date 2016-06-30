package com.ewareza.shapegame.domain.generator;

import android.graphics.Rect;
import com.ewareza.shapegame.app.utils.GameUtils;
import com.ewareza.shapegame.domain.objects.Oval;
import com.ewareza.shapegame.domain.objects.Rectangle;
import com.ewareza.shapegame.domain.objects.Shape;

public class OvalGenerator extends ShapeGenerator {
    private static final OvalGenerator INSTANCE = new OvalGenerator(Oval.class);

    public OvalGenerator(Class<? extends Shape> shapeClass) {
        super(shapeClass);
    }

    public static ShapeGenerator getInstance() {
        return INSTANCE;
    }

    @Override
    public Shape generateRandomShape() {
        Rectangle rectangle = (Rectangle) RectangleGenerator.getInstance().generateRandomShape();
        Rect rect = new Rect(rectangle.getLeft(), rectangle.getTop(), rectangle.getRight(), rectangle.getBottom());
        Oval oval = new Oval(ColorGenerator.generateColor(), rect);

        return oval;
    }

    @Override
    public Shape generateGameTitleShape() {
        Rectangle rectangle = (Rectangle) RectangleGenerator.getInstance().generateGameTitleShape();
        Rect rect = rectangle.asRect();

        return new Oval(GameUtils.getDefaultShapeColor(), rect);
    }
}
